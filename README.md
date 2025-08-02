# qMetamodelGenError
Just a simple project to show an error in querydsl metamodel generation using maven and the @NotNull annotation


This is a pair of entities related mutually through an interface and an abstract class, using this stack:
- java 21 (OpenJDK Runtime Environment build 21.0.8+9)
- maven 3.9.10
- jakarta-persistence 3.1.0
- jakarta-annotation 2.1.1
- querydsl.version 5.1.0 (querydsl-apt, querydsl-jpa)
- hibernate-validator 8.0.2.Final

Yoy can reproduce the error running:
mvn -X -P METAMODEL clean compile

The error:

```
Caused by: java.lang.IllegalStateException: Did not find type  T1 in AbstractE2<?> for E2Impl
    at com.querydsl.codegen.TypeResolver.resolveVar (TypeResolver.java:74)
    at com.querydsl.codegen.TypeResolver.resolve (TypeResolver.java:45)
    at com.querydsl.codegen.Property.createCopy (Property.java:92)
    at com.querydsl.codegen.EntityType.include (EntityType.java:262)
    at com.querydsl.apt.AbstractQuerydslProcessor.addSupertypeFields (AbstractQuerydslProcessor.java:416)

```

And the important thing here is just the space before T1 (" T1"). 

I don't know why, but if you remove de validation annotation (@NotNull) then there is no error.
If the @NotNull is keep, on debugging you can see that the method com.querydsl.codegen.TypeResolver.resolveVar(Type resolved, String varName, Type declaringType, EntityType context)  uses this:

```
if (Objects.equals(getVarName(param), varName)) {
    index = i;
}
```
where varName = "T" and getVarName(param) = " T" (whith that white space at the start), so the equals returns false.

Maybe something is wrong with Hibernate validation 8.0.2.Final, or OpenJDK 21.0.8+9 or simply, querydsl needs to trim the strings:

```
if (Objects.equals(getVarName(param).trim(), varName.trim())) {
    index = i;
}
```

