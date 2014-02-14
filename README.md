#Smartsheet Java SDK

This is a Java SDK to simplify connecting to [Smartsheet API](http://www.smartsheet.com/developers/api-documentation) services in a Java applications.

##Installation
---

There are 3 different approaches that can be used to install the SDK. We offer these 3 approaches so that the SDK can be installed in a manner most familiar or best for the environment that it is being used in. Only one of the following installation steps need to be choosen:

1. [Maven](#1-maven)
2. [Download Jar File or Jar with Dependencies](#2-download-jar-file)
3. [Compile From Source](#3-compile-from-source)

###1. Maven
Add the SDK as a dependency in your project.

```xml
   <dependency>
      <groupId>com.smartsheet</groupId>
      <artifactId>smartsheet-java-sdk</artifactId>
      <version>1.0</version>
      <scope>compile</scope>
   </dependency>
```

###2. Download Jar File

* [The SDK packaged in a jar with Dependencies](TODO) built in.
* [The SDK packaged in a jar](TODO). This jar requires that all of the following dependencies are manually added to the path.

        Apache HttpComponents 4.3.2
        Simple Logging Facade for Java 4.3.2
        Jackson FasterXML 2.2.3
        Jackson Core 2.2.3

###3. Compile from Source
The source code for the jar can be downloaded from Github and then compiled. This can be accomplished using [git](http://git-scm.com/) and [maven](http://maven.apache.org/) with the following 3 steps.

```bash
git clone http:\\FIXME:\\
cd smartsheet-java-sdk
mvn package
```
# Documentation
The SDK API documentation can be viewed online at the following location: [TODO]()

The documentation can also be downloaded as a jar file at: [TODO]()

##Example Usage
---

```java
public void main(String[] args){/*TODO*/}
```

## Contributing
---
If you would like to contribute a change to the SDK, please fork a branch and then submit a pull request. [More info here](https://help.github.com/articles/using-pull-requests).

##Support
---
If you have any questions or issues with this SDK please feel free to send us an email at: api@smartsheet.com