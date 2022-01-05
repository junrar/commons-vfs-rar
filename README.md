[![Build Status](https://github.com/junrar/commons-vfs-rar/workflows/CI/badge.svg?branch=master)](https://github.com/junrar/commons-vfs-rar/actions?query=workflow%3ACI+branch%3Amaster)
[![Maven Central](https://img.shields.io/maven-central/v/com.github.junrar/commons-vfs-rar)](https://search.maven.org/artifact/com.github.junrar/commons-vfs-rar)
[![javadoc](https://javadoc.io/badge2/com.github.junrar/commons-vfs-rar/javadoc.svg)](https://javadoc.io/doc/com.github.junrar/commons-vfs-rar)
[![codecov](https://codecov.io/gh/junrar/commons-vfs-rar/branch/master/graph/badge.svg)](https://codecov.io/gh/junrar/commons-vfs-rar)

# Apache Commons VFS RAR Provider

This project provides a [VFS Provider](https://commons.apache.org/proper/commons-vfs/apidocs/org/apache/commons/vfs2/provider/FileProvider.html)
for RAR files.

It has been carved out of [Junrar](https://github.com/junrar/junrar).

## Installation

<table>
<tr>
    <td>Gradle</td>
    <td>
        <pre>implementation "com.github.junrar:commons-vfs-rar:{version}"</pre>
    </td>
</tr>
<tr>
    <td>Gradle (Kotlin DSL)</td>
    <td>
        <pre>implementation("com.github.junrar:commons-vfs-rar:{version}")</pre>
        </td>
</tr>
<tr>
    <td>Maven</td>
    <td>
        <pre>&lt;dependency&gt;
    &lt;groupId&gt;com.github.junrar&lt;/groupId&gt;
    &lt;artifactId&gt;commons-vfs-rar&lt;/artifactId&gt;
    &lt;version&gt;{version}&lt;/version&gt;
&lt;/dependency&gt;</pre>
    </td>
</tr>
</table>

where `{version}` corresponds to version as below:

| commons-vfs-version                                                                                                                                                                             | Java version | Junrar version |
|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|:------------:|:--------------:|
| [![Maven Central](https://img.shields.io/maven-central/v/com.github.junrar/commons-vfs-rar)](https://search.maven.org/artifact/com.github.junrar/commons-vfs-rar)                               | 8            |      7.4.0     |
| [![Maven Central](https://img.shields.io/maven-central/v/com.github.junrar/commons-vfs-rar?versionPrefix=1.0.0)](https://search.maven.org/artifact/com.github.junrar/commons-vfs-rar/1.0.0/jar) | 8            |      5.0.0     |

If you need Java 6 support, you can get version `4.0.0` of Junrar which bundled VFS
support: [![Maven Central](https://img.shields.io/maven-central/v/com.github.junrar/junrar?versionPrefix=4.0.0)](https://search.maven.org/artifact/com.github.junrar/junrar/4.0.0/jar)

