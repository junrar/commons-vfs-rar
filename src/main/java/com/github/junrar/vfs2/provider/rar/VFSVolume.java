package com.github.junrar.vfs2.provider.rar;

import com.github.junrar.Archive;
import com.github.junrar.volume.Volume;
import com.github.junrar.io.SeekableReadOnlyByteChannel;
import com.github.junrar.io.SeekableReadOnlyInputStream;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.RandomAccessContent;
import org.apache.commons.vfs2.util.RandomAccessMode;

import java.io.IOException;


/**
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class VFSVolume implements Volume {
    private final Archive archive;
    private final FileObject file;

    /**
     * @param archive .
     * @param file    .
     */
    public VFSVolume(final Archive archive, final FileObject file) {
        this.archive = archive;
        this.file = file;
    }

    @Override
    public SeekableReadOnlyByteChannel getChannel() throws IOException {
        SeekableReadOnlyByteChannel input = null;
        try {
            final RandomAccessContent rac = this.file.getContent().getRandomAccessContent(RandomAccessMode.READ);
            input = new RandomAccessContentAccess(rac);
        } catch (final Exception e) {
            input = new SeekableReadOnlyInputStream(this.file.getContent().getInputStream());
        }
        return input;
    }

    @Override
    public long getLength() {
        try {
            return this.file.getContent().getSize();
        } catch (final FileSystemException e) {
            return -1;
        }
    }

    @Override
    public Archive getArchive() {
        return this.archive;
    }

    /**
     * @return the file
     */
    public FileObject getFile() {
        return this.file;
    }
}
