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

Annotations placed on packages affect tests for all classes and methods under test in that package.
Annotations placed on classes affect tests for that class and all it's methods under test, overriding package level annotations.
Annotations placed on methods affect just that method under test, overriding package and class level annotations.

The annotations will be respected by Diffblue Cover via both command line and IntelliJ Plugin.
When used from the command line in conjunction with equivalent options then the command line options take priority over the annotations found.

## Mocking Annotations

Mocking annotations allow fine grained control over what mocking should be preferred when testing.

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

> [!NOTE]
> Using `@InTestsMock` has the same effect as, and can be overridden by, Cover CLI command line options:
>
> ```
> dcover create --mock ClassToMock --disable-mock-inputs ClassToForbidMocking
> ```

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

> [!NOTE]
> Note that using `@InTestsMockConstruction` has the same effect as, and can be overridden by, Cover CLI command line option:
>
> ```
> dcover create --mock-construction ClassToMockConstruction
> ```

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

> [!NOTE]
> Using `@InTestsMockStatic` has the same effect as, and can be overridden by, Cover CLI command line option:
>
> ```
> dcover create --mock-static ClassToMockStatic
> ```

## Custom Input Annotations

Custom input annotations allow particular inputs to be recommended to Diffblue Cover when writing tests.

### Using `@InTestsUseEnum`

The `@InTestsUseEnum` annotation allows the user to recommend specific `enum` literal values to use in tests.
Sometimes this can be useful to control the values used for cosmetic reasons, but it can also be useful when Cover is unable to identify values to cover all cases.

```java
public static boolean isDateOrTimeBased(@InTestsUseEnums({"SECONDS", "YEARS", "FOREVER"}) ChronoUnit chronoUnit) {
    return chronoUnit.isDateBased() || chronoUnit.isTimeBased();
}
```

### Using `@InTestsUseClasses`

The `@InTestsUseClasses` annotation allows the user to recommend specific `Class` literal values to use in tests.
Sometimes this can be useful to control the values used for cosmetic reasons, but it can also be useful when Cover is unable to identify values to cover all cases.
For example the following method is annotated with an example class literal to achieve a positive test:

```java
public static boolean isAnnotation(@InTestsUseClasses(Nullable.class) Class<?> theClass) {
    return theClass.isAnnotation();
}
```

### Using `@InTestsUseStrings`

The `@InTestsUseStrings` annotation allows the user to recommend specific `String` values to use in tests.
Sometimes this can be useful to control the values used for cosmetic reasons, but it can also be useful when Cover is unable to identify values to cover all cases.
For example the following method is annotated with some genuine examples of song titles that can be used to achieve coverage:

```java
public static boolean isDayRelatedSongTitle(@InTestsUseStrings({"I Don't Like Mondays", "Here Comes The Weekend"}) String title) {
    return Stream.of(DayOfWeek.values())
            .map(DayOfWeek::name)
            .map(String::toLowerCase)
            .anyMatch(title.toLowerCase()::contains);
}
```

### Using `@InTestsUseCharacters`

The `@InTestsUseCharacters` annotation allows the user to recommend specific `char` values to use in tests.
Sometimes this can be useful to control the values used for cosmetic reasons, but it can also be useful when Cover is unable to identify values to cover all cases.
For example the following method is annotated with a genuine examples characters that make up a Unicode surrogate pair that can be used to achieve a positive test:

```java
@Nullable
public static Integer toNullableCodePoint(
        @InTestsUseCharacters('\uD801') char high,
        @InTestsUseCharacters('\uDC37') char low) {
    if (Character.isSurrogatePair(high, low)) {
        return Character.toCodePoint(high, low);
    }
    return null;
}
```

### Using `@InTestsUseBytes`

The `@InTestsUseBytes` annotation allows the user to recommend specific `byte` values to use in tests.
Sometimes this can be useful to control the values used for cosmetic reasons, but it can also be useful when Cover is unable to identify values to cover all cases.
For example the following method is annotated to use a specific preferred value:

```java
public static String toUpperHexString(@InTestsUseBytes((byte)0xD1) byte input) {
    return Long.toHexString(input).toUpperCase();
}
```

### Using `@InTestsUseShorts`

The `@InTestsUseShorts` annotation allows the user to recommend specific `short` values to use in tests.
Sometimes this can be useful to control the values used for cosmetic reasons, but it can also be useful when Cover is unable to identify values to cover all cases.
For example the following method is annotated to use a specific preferred value:

```java
public static String toUpperHexString(@InTestsUseShorts((short)0xD1FF) short input) {
    return Long.toHexString(input).toUpperCase();
}
```

### Using `@InTestsUseIntegers`

The `@InTestsUseIntegers` annotation allows the user to recommend specific `int` values to use in tests.
Sometimes this can be useful to control the values used for cosmetic reasons, but it can also be useful when Cover is unable to identify values to cover all cases.
For example the following method is annotated to use a specific preferred value:

```java
public static String toUpperHexString(@InTestsUseIntegers(0xD1FFB) int input) {
    return Long.toHexString(input).toUpperCase();
}
```

### Using `@InTestsUseLongs`

The `@InTestsUseLongs` annotation allows the user to recommend specific `long` values to use in tests.
Sometimes this can be useful to control the values used for cosmetic reasons, but it can also be useful when Cover is unable to identify values to cover all cases.
For example the following method is annotated to use a specific preferred value:

```java
public static String toUpperHexString(@InTestsUseLongs(0xD1FFBL) long input) {
    return Long.toHexString(input).toUpperCase();
}
```

### Using `@InTestsUseFloats`

The `@InTestsUseFloats` annotation allows the user to recommend specific `float` values to use in tests.
Sometimes this can be useful to control the values used for cosmetic reasons, but it can also be useful when Cover is unable to identify values to cover all cases.
For example the following method is annotated to use a specific preferred value:

```java
public static boolean isNearPi(@InTestsUseFloats(3.14159f) float input) {
    return Float.toString(input).startsWith("3.14");
}
```

### Using `@InTestsUseDoubles`

The `@InTestsUseDoubles` annotation allows the user to recommend specific `double` values to use in tests.
Sometimes this can be useful to control the values used for cosmetic reasons, but it can also be useful when Cover is unable to identify values to cover all cases.
For example the following method is annotated to use a specific preferred value:

```java
public static boolean isNearPi(@InTestsUseDoubles(Math.PI) float input) {
    return Double.toString(input).startsWith("3.14");
}
```

## Interesting Value Annotations

Interesting value annotations allow users to promote existing fields and methods to be identified and used in particular roles by Diffblue Cover when writing tests.

### Using `@InterestingTestFactory`

Indicates the annotated method as a useful factory method for use in tests.
Cover will automatically recognise factory methods that simply return a newly created instance, but may not identify more complicated factories.
This annotation allows such factory methods to be manually annotated so that Cover considers them for producing inputs.
For example the following method under test takes a `User` as input, but the `User` constructor is private and Cover doesn't naturally consider `ofStaff(String)` to be a safe factory method to call.
By annotating the `ofStaff(String)` with `@InterstingTestFactory` we can tell Cover that this should be considered a good factory method to use in tests.

```java
public String getUserDisplayString(User user) {
    if (user.manager) {
        return user.username + " (manager)";
    }
    else {
        return user.username;
    }
}

class User {
    private static Map<String, User> staff = new HashMap<String, User>();

    @InterestingTestFactory
    public static User ofStaff(String name) {
        return staff.computeIfAbsent(name, ignored -> new User(name, false));
    }

    public final String username;
    public final boolean manager;

    private User(String username, boolean manager) {
        this.username = username;
        this.manager = manager;
    }
}
```

