[![Build Status](https://github.com/junrar/commons-vfs-rar/workflows/CI/badge.svg?branch=master)](https://github.com/junrar/commons-vfs-rar/actions?query=workflow%3ACI+branch%3Amaster)
[![Download](https://api.bintray.com/packages/bintray/jcenter/com.github.junrar%3Acommons-vfs-rar/images/download.svg) ](https://bintray.com/bintray/jcenter/com.github.junrar%3Acommons-vfs-rar/_latestVersion)
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

| commons-vfs-version | Java version | Junrar version |
|---------------------|:------------:|:--------------:|
| [![Download](https://api.bintray.com/packages/bintray/jcenter/com.github.junrar%3Acommons-vfs-rar/images/download.svg) ](https://bintray.com/bintray/jcenter/com.github.junrar%3Acommons-vfs-rar/_latestVersion) | 8            |      7.4.0     |
| [![Download](https://api.bintray.com/packages/bintray/jcenter/com.github.junrar%3Acommons-vfs-rar/images/download.svg?version=1.0.0) ](https://bintray.com/bintray/jcenter/com.github.junrar%3Acommons-vfs-rar/1.0.0/link) | 8            |      5.0.0     |

If you need Java 6 support, you can get version `4.0.0` of Junrar which bundled VFS support: [![Download](https://api.bintray.com/packages/bintray/jcenter/com.github.junrar%3Ajunrar/images/download.svg?version=4.0.0) ](https://bintray.com/bintray/jcenter/com.github.junrar%3Ajunrar/4.0.0/link)

