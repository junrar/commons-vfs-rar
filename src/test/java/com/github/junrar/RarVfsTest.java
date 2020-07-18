package com.github.junrar;

import com.github.junrar.exception.RarException;
import org.apache.commons.io.IOUtils;
import org.apache.commons.vfs2.FileContent;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.FileSystemManager;
import org.apache.commons.vfs2.VFS;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class RarVfsTest {

    @Test
    public void givenRarFile_whenAccessingViaVFS_thenChildrenAreAvailable() throws IOException {
        FileSystemManager manager = VFS.getManager();

        String path = getClass().getResource("rar4.rar").getPath();

        FileObject rarFile = manager.resolveFile("rar:" + path);

        List<FileObject> children = Arrays.asList(rarFile.getChildren());

        assertThat(children).hasSize(2);
        assertThat(children.stream().map(f -> f.getName().getBaseName()))
            .containsExactlyInAnyOrder("FILE1.TXT", "FILE2.TXT");

        for (int i = 1; i <= 2; i++) {
            int finalI = i;
            FileContent fileContent = children.stream()
                .filter(f -> f.getName().getBaseName().equals("FILE" + finalI + ".TXT"))
                .findFirst().orElse(null).getContent();

            assertThat(fileContent.getSize()).isEqualTo(7);

            InputStream is = fileContent.getInputStream();
            assertThat(IOUtils.toString(is)).isEqualTo("file" + finalI + "\r\n");
        }
    }

    @Test
    public void givenRar5File_whenAccessingViaVFS_thenExceptionIsThrown() throws IOException {
        FileSystemManager manager = VFS.getManager();

        String path = getClass().getResource("rar5.rar").getPath();

        Throwable thrown = catchThrowable(() -> manager.resolveFile("rar:" + path));

        assertThat(thrown)
            .isInstanceOf(FileSystemException.class)
            .hasCauseInstanceOf(RarException.class);
    }
}
