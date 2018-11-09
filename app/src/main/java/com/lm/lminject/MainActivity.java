package com.lm.lminject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.lm.annotation.inject.Inject;
import com.lm.annotation.inject.InjectorHelper;
import com.lm.annotation.inject.Injectors;
import com.lm.annotation.provide.Providers;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView tv = findViewById(R.id.tv);

        InjectorHelper.inject(this, new UserNameProvide());
        InjectorHelper.inject(this, new PasswordProvide());

        String text = new StringBuilder()
                .append("string = ").append(string).append("\n")
                .append("stringArray = ").append(Arrays.toString(stringArray)).append("\n")
                .append("stringList = ").append(stringList).append("\n")
                .append("integer = ").append(integer).append("\n")
                .append("integerArray = ").append(Arrays.toString(integerArray)).append("\n")
                .append("long = ").append(aLong).append("\n")
                .append("longArray = ").append(Arrays.toString(longArray)).append("\n")
                .append("char = ").append(Integer.valueOf(aChar)).append("\n")
                .append("charArray = ").append(Arrays.toString(charArray)).append("\n")
                .append("boolean = ").append(aBoolean).append("\n")
                .append("booleanArray = ").append(Arrays.toString(booleanArray)).append("\n")
                .append("byte = ").append(aByte).append("\n")
                .append("byteArray = ").append(Arrays.toString(byteArray)).append("\n")
                .append("float = ").append(aFloat).append("\n")
                .append("floatArray = ").append(Arrays.toString(floatArray)).append("\n")
                .append("double = ").append(aDouble).append("\n")
                .append("doubleArray = ").append(Arrays.toString(doubleArray)).append("\n")
                .toString();
        tv.setText(text.toString());
    }

}
