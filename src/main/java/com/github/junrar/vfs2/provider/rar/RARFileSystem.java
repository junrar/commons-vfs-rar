/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.junrar.vfs2.provider.rar;

import com.github.junrar.Archive;
import com.github.junrar.exception.RarException;
import com.github.junrar.rarfile.FileHeader;
import org.apache.commons.vfs2.Capability;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystem;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.FileSystemOptions;
import org.apache.commons.vfs2.provider.AbstractFileName;
import org.apache.commons.vfs2.provider.AbstractFileSystem;
import org.apache.commons.vfs2.provider.UriParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A read-only file system for RAR files.
 *
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class RARFileSystem extends AbstractFileSystem implements FileSystem {
    private final FileObject parentLayer;

    private Archive archive;
    private final Map<String, FileHeader> files = new HashMap<>();

    public RARFileSystem(
        final AbstractFileName rootName,
        final FileObject parentLayer,
        final FileSystemOptions fileSystemOptions
    ) throws FileSystemException {
        super(rootName, parentLayer, fileSystemOptions);
        this.parentLayer = parentLayer;
    }

    @Override
    public void init() throws FileSystemException {
        super.init();

        try {
            try {
                this.archive = new Archive(new VFSVolumeManager(this.parentLayer), null, null);
                // Build the index
                final List<RARFileObject> strongRef = new ArrayList<>(
                    100);
                for (final FileHeader header : this.archive.getFileHeaders()) {
                    final AbstractFileName name = (AbstractFileName) getFileSystemManager()
                        .resolveName(
                            getRootName(),
                            UriParser.encode(header.getFileName()));

                    // Create the file
                    RARFileObject fileObj;
                    if (header.isDirectory() && getFileFromCache(name) != null) {
                        fileObj = (RARFileObject) getFileFromCache(name);
                        fileObj.setHeader(header);
                        continue;
                    }

                    fileObj = createRARFileObject(name, header);
                    putFileToCache(fileObj);
                    strongRef.add(fileObj);
                    fileObj.holdObject(strongRef);

                    // Make sure all ancestors exist
                    RARFileObject parent;
                    for (AbstractFileName parentName = (AbstractFileName) name
                        .getParent(); parentName != null; fileObj = parent, parentName = (AbstractFileName) parentName
                        .getParent()) {
                        // Locate the parent
                        parent = (RARFileObject) getFileFromCache(parentName);
                        if (parent == null) {
                            parent = createRARFileObject(parentName, null);
                            putFileToCache(parent);
                            strongRef.add(parent);
                            parent.holdObject(strongRef);
                        }

                        // Attach child to parent
                        parent.attachChild(fileObj.getName());
                    }
                }

            } catch (final RarException | IOException e) {
                throw new FileSystemException(e);
            }
        } finally {
            // closeCommunicationLink();
        }
    }

    protected RARFileObject createRARFileObject(final AbstractFileName name,
                                                final FileHeader header) throws FileSystemException {
        return new RARFileObject(name, this.archive, header, this);
    }

    @Override
    protected void doCloseCommunicationLink() {
        try {
            this.archive.close();
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns the capabilities of this file system.
     */
    @Override
    protected void addCapabilities(final Collection<Capability> caps) {
        caps.addAll(RARFileProvider.capabilities);
    }

    /**
     * Creates a file object.
     */
    @Override
    protected FileObject createFile(final AbstractFileName name)
        throws FileSystemException {
        final String path = name.getPath().substring(1);
        if (path.length() == 0) {
            return new RARFileObject(name, this.archive, null, this);
        } else if (this.files.containsKey(name.getPath())) {
            return new RARFileObject(name, this.archive, this.files.get(name.getPath()),
                this);
        }
        return null;
    }
}
