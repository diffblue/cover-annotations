# Cover Annotations

Cover Annotations allows users to annotate their Java codebase with advice on how best to test it.
In turn this can be used by [Diffblue Cover](https://diffblue.com/cover) to tune the tests it writes.

## Installation

### Maven

In order to use the annotations in your Maven project then the Diffblue repository and `cover-annotations` dependency will need to be added into your `pom.xml`:

```xml
<dependencies>
    <dependency>
        <groupId>com.diffblue.cover</groupId>
        <artifactId>cover-annotations</artifactId>
        <version>1.0.0</version>
    </dependency>
</dependencies>
```

### Gradle

In order to use the annotations in your Gradle project then the Diffblue repository and `cover-annotations` dependency will need to be added into your `build.gradle`:

```gradle
dependencies {
    implementation("com.diffblue.cover:cover-annotations:1.0.0")
}
```

Or similarly for `build.gradle.kts`:

```
dependencies {
    implementation("com.diffblue.cover:cover-annotations:1.0.0")
}
```

## Annotations

Annotations placed on packages affect tests for all classes and methods under test in that package.
Annotations placed on classes affect tests for that class and all it's methods under test, overriding package level annotations.
Annotations placed on methods affect just that method under test, overriding package and class level annotations.

| Annotation                  | Equivalent `dcover create` option                 |
|:----------------------------|:--------------------------------------------------|
| `@InTestsMock`              | `--mock`, `--disable-mock-inputs`                 |
| `@InTestsMockConstruction`  | `--mock-construction`                             |
| `@InTestsMockStatic`        | `--mock-static`                                   |

The annotations will be respected by Diffblue Cover via both command line and IntelliJ Plugin.
When used from the command line in conjunction with equivalent options then the command line options take priority over the annotations found.

### Using `@InTestsMock`

Perhaps you have a method that Diffblue Cover would ordinarily test using an `Integer` but you'd prefer to see it tested using `Mockito.mock(..)`.
In this case you could annotate the method (or class, or package) to recommend mocking `Number`:

```java
public class ClassUnderTest {
  @InTestsMock(Number.class)
  public static String methodUnderTest(Number number) {
    return String.valueOf(number.intValue());
  }
}
```

Conversely, if Diffblue Cover normally does mock a particular class, and you have a particular location where it shouldn't be then you can forbid it:

```java
public class ClassUnderTest { 
  @InTestsMock(value = Number.class, decision = MockDecision.FORBIDDEN)
  public static String methodUnderTest(Number number) {
    return String.valueOf(number.intValue());
  }
}
```

### Using `@InTestsMockConstruction`

Perhaps you have a method that Diffblue Cover is unable to test, and you think it could make more progress using `Mockito.mockConstruction(Random.class)`.
In this case you could annotate the method (or class, or package) to recommend mocking construction of `Random`:

```java
public class ClassUnderTest {
  @InTestsMockConstruction(Random.class)
  public static int methodUnderTest() {
    return new Random().nextInt();
  }
}
```

### Using `@InTestsMockStatic`

Perhaps you have a method that Diffblue Cover is unable to test, and you think it could make more progress using `Mockito.mockStatic(UUID.class)`.
In this case you could annotate the method (or class, or package) to recommend mocking static methods of `UUID`:

```java
public class ClassUnderTest {
  @InTestsMockStatic(UUID.class)
  public static Path methodUnderTest() {
    return Paths.get(UUID.randomUUID() + ".zip");
  }
}
```
