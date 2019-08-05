# Run JMETER

```java
jmeter -n -t your_script.jmx
```

## In Maven

```java
    <build>
        <plugins>
            <plugin>
                <groupId>com.lazerycode.jmeter</groupId>
                <artifactId>jmeter-maven-plugin</artifactId>
                <version>1.4.1</version>
                <executions>
                    <execution>
                        <id>jmeter-tests</id>
                        <phase>verify</phase>
                        <goals>
                            <goal>jmeter</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
```

## Program JMETER

## ToDoList

- [] JobService: run the defined test case
  * [] 运行JMETER 任务
    1. RENDER JMETER file
    2. SAVE JMETER FILE to local place or GIT
    3. Run in Machines
  * [] Stop all the jobs
  * [] Get Jobs Status

- [] PerfTestCaseService : save the performance test case

  * Definition
    * api: API Definition/API ID
    * scenario definition : JSON Definition
  * JMETER Content/JMETER FileLocation/JMETER FILE UPLOAD/

- [] API Definitions
- [] API Projects
- [] Data Rest Controller Code Generators
