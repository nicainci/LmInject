# LmInject
## Android注入框架Demo

#### 供应注解`@Provide`，用来提供参数，其中provideName为KEY
```
    @Provide(provideName = "integer")
    int integer = 1024;
    
    @Provide(provideName = "integerArray")
    int[] integerArray = new int[]{1024, 1025};
    
    @Provide(provideName = "long")
    long aLong = 2048;
    
    @Provide(provideName = "longArray")
    long[] longArray = new long[]{2048, 2049};
    
    @Provide(provideName = "char")
    char aChar = 'A';
    
    @Provide(provideName = "charArray")
    char[] charArray = new char[]{'A', 'B', 'C'};
    
    @Provide(provideName = "boolean")
    boolean aBoolean = true;
    
    @Provide(provideName = "booleanArray")
    boolean[] booleanArray = new boolean[]{true, false, true, false};
    
    @Provide(provideName = "byte")
    byte aByte = 2;
    
    @Provide(provideName = "byteArray")
    byte[] byteArray = new byte[]{2, 3, 4};
    
    @Provide(provideName = "string")
    String string = "str";
    
    @Provide(provideName = "stringArray")
    String[] stringArray = new String[]{"str1", "str2"};
    
    @Provide(provideName = "stringList")
    List<String> stringList = new ArrayList<>();
    
    @Provide(provideName = "float")
    float aFloat = 123.123f;
    
    @Provide(provideName = "floatArray")
    float[] floatArray = new float[]{1, 2, 3};
    
    @Provide(provideName = "double")
    double aDouble = 1234.1234d;
    
    @Provide(provideName = "doubleArray")
    double[] doubleArray = new double[]{1, 2, 3, 4};
```

#### 注入注解`@Inject`，用来将供应注解`@Provide`提供的参数注入进来，其中provideName为KEY
```
    @Inject(provideName = "string")
    String string;
    @Inject(provideName = "stringArray")
    String[] stringArray;
    @Inject(provideName = "stringList")
    List<String> stringList;
    @Inject(provideName = "integer")
    int integer;
    @Inject(provideName = "integerArray")
    int[] integerArray;
    @Inject(provideName = "long")
    long aLong;
    @Inject(provideName = "longArray")
    long[] longArray;
    @Inject(provideName = "char")
    char aChar;
    @Inject(provideName = "charArray")
    char[] charArray;
    @Inject(provideName = "boolean")
    boolean aBoolean;
    @Inject(provideName = "booleanArray")
    boolean[] booleanArray;
    @Inject(provideName = "byte")
    byte aByte;
    @Inject(provideName = "byteArray")
    byte[] byteArray;
    @Inject(provideName = "float")
    float aFloat;
    @Inject(provideName = "floatArray")
    float[] floatArray;
    @Inject(provideName = "double")
    double aDouble;
    @Inject(provideName = "doubleArray")
    double[] doubleArray;
```

#### 注入方法
```
    InjectorHelper.inject(MainActivity.this, new AProvide());
```