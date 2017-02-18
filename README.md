# Docerina

Docerina is the API documentation generator tool of the Ballerina language. It currently supports generating API 
documentation in HTML format and it can be extended to support additional output formats as required.

## Generating Ballerina API Documentation

Docerina is distributed with [Ballerina Tools Distribution](https://github.com/ballerinalang/tools-distribution) and [Docerina Maven Plugin] (https://github.com/ballerinalang/plugin-maven/tree/master/docerina-maven-plugin). 

# Using the Ballerina Tools Distribution

Ballerina doc command can be used to generate the API documentation for your Ballerina programs.

```sh
$ ballerina help doc
generates API documentation for Ballerina packages

Usage:
ballerina doc <sourcepath>... [-o outputdir -e excludedpackages -v]

  sourcepath:
  Either the paths to the directories where Ballerina source files reside or a path to a Ballerina file which does not belong to a package

Flags:
  --output, -o     path to the output directory where the API documentation will be written to
  --exclude, -e    comma separated list of package names to be filtered from the documentation
  --verbose, -v    enable debug level logs
```

## Usage

**Example 1:** Generate the API documentation for the given Ballerina source directories and files. This would generate the API docs at `{currentdir}/api-docs/html/` directory.
```sh
$ ballerina doc ../../../connectors/twitter/src/ ../../../connectors/soap/src/ test.bal
```
**Example 2:** Generate the API documentation for the given Ballerina source directories and files and copy them to the `{currentdir}/docs` directory.
```sh
$ ballerina doc ../../../connectors/twitter/src/ ../../../connectors/soap/src/ test.bal  -o docs
```
**Example 3:** Generate the API documentation for the given Ballerina source directories and files, excluding the `org.wso2.ballerina.connectors.twitter` package and copy them to the `{currentdir}/docs` directory.
```sh
$ ballerina doc ../../../connectors/twitter/src/ ../../../connectors/soap/src/ test.bal  -o docs -e org.wso2.ballerina.connectors.twitter
```
**Example 4:** Generate the API documentation for the given Ballerina source directories and files and copy them to the `{currentdir}/docs` directory while printing debug level logs of Docerina to the stdout.
```sh
$ ballerina doc ../../../connectors/twitter/src/ ../../../connectors/soap/src/ test.bal  -o docs -v
```
# Using the Docerina Maven Plugin

The Docerina Maven plugin is used to generate Ballerina API documentation at the build time of a Ballerina Package.

## Introduction

The Docerina Maven plugin is a Maven plugin, which is used within the Ballerina language eco-system. The Maven goals that are achieved through this plugin are explained below:

* `docerina`: For generating Ballerina API documentation. See the instructions on [configuring the `docerina` Maven goal](#configuring-the-docerina-maven-goal). 

This goal is executed during the compile phase in the default life cycle of the Maven build. You have the flexibility to configure the behavior of the plugin by passing the relevant parameters to this Maven goal.

### Configuring the docerina Maven goal

A sample pom.xml file configuration of the docerina Maven goal is shown below.

    <build>
      <plugins>
          <plugin>
                <groupId>org.ballerinalang</groupId>
                <artifactId>docerina-maven-plugin</artifactId>
                <version>${docerina.maven.plugin.version}</version>
                <executions>
                    <execution>
                        <phase>compile</phase>
                        <goals>
                            <goal>docerina</goal>
                        </goals>
                        <configuration>
                            <outputDir>${output.dir}</outputDir>
                            <sourceDir>${generated.ballerina.source.directory}</sourceDir>
                            <packageFilter>org.ballerinalang.xyz</packageFilter>
                            <debugDocerina>false</debugDocerina>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
    
Supported list of configuration parameters are listed below;

* `sourceDir`: Comma separated list of the paths to the directories where Ballerina source files reside or 
paths to Ballerina files which does not belong to a package.

 MANDATORY property.
 Example: `<sourceDir>/home/docerina/abc/src,/home/docerina/xyz/src</sourceDir>`
 
* `outputDir`: Points to the location where the generated API docs will be stored. 

 OPTIONAL property.
 DEFAULT value is the ${project.build.directory} which is the target directory.
 Example: `<outputDir>/home/docerina/output</outputDir>`

* `packageFilter`: Comma separated list of package names to be filtered from the documentation.

 OPTIONAL property.
 DEFAULT value is none.
 Example: `<packageFilter>org.ballerinalang.abc.a,org.ballerinalang.xyz.x</packageFilter>`

* `debugDocerina`: Enable debug level logs.

 OPTIONAL property.
 DEFAULT value is false.
 Example: `<debugDocerina>true</debugDocerina>`
 
 
## Setting up a Docerina Development Environment

Follow below steps to setup a Docerina development environment:

- Clone the Ballerina Git repository:
  
  ```
  git clone https://github.com/ballerinalang/ballerina
  ```
  
- Build Ballerina and extract the distribution:
 
  ```
  cd ballerina
  mvn clean install
  cd modules/distribution/target/
  unzip ballerina-<version>.zip
  cd ballerina-<version> # Refer this as [ballerina-home]
  ```

- Clone the Docerina Git repository:
  
  ```
  cd ..
  git clone https://github.com/ballerinalang/docerina.git
  ```

- Build Docerina and copy the Docerina JAR file to the Ballerina distribution lib folder:
  
  ```
  cd docerina
  mvn clean install
  cd target
  cp docerina-<version>.jar [ballerina-home]/bre/lib/
  ```

- Navigate to Ballerina bin directory and execute the bellow command to generate API documentation:
  
  ```
  cd [ballerina-home]/bin
  ./ballerina doc [ballerina-package-path] # absolute file path of the ballerina source package
  ```
  
- Execute the below command to attach a remote debugging session to debug Docerina:

  ```
  cd [ballerina-home]/bin
    ./ballerina --debug 5005 doc [ballerina-package-path] # absolute file path of the ballerina source package
  ```