# Barista

Barista is a CLI that can assist you while you build your Java application.

## Installation

Clone this repository:

```bash
git clone https://github.com/wasthishelpful/barista
```

Then compile Barista.java:

```bash
javac Barista.java
```

## Usage

Run Barista:

```bash
java Barista.java
```

## Use within maven project as plugin:

Configuration:

```bash
<plugin>
   <groupId>de.hegmanns</groupId>
   <artifactId>barista-plugin</artifactId>
   <version>0.0.1-SNAPSHOT</version>
   <executions>
      <execution>
         <id>1</id>
         <phase>compile</phase>
         <goals><goal>generate</goal></goals>
      </execution>
      <execution>
         <id>2</id>
         <phase>clean</phase>
         <goals><goal>clean</goal></goals>
      </execution>
   </executions>
   <configuration>
      <baristaClass><!-- you can  put at most one element in the configuration-->
         <type>Controller</type>
         <name>Rabbit</name>
      </baristaClass>
      <baristaClasses><!-- you can put at most one element in the configuration-->
         <baristaClass><!-- here you define the classes to generate-->
            <type>Controller</type>
            <name>Lion</name>
         </baristaClass>
         <baristaclass>
            <type>Controller</type>
            <name>Bird</name>
         </baristaclass>
      </baristaClasses>
   </configuration>
</plugin>
```



## Contributing

Pull requests are welcome. Check the [issues](https://github.com/wasthishelpful/barista/issues) to see how you can help (and feel free to open new ones).

## License

Barista is open source software [licensed as MIT](LICENSE.md).
