// References: strong, weak, soft, and phantom

package com.references;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

// Class Example
class Example {

    // Method
    void print(){
        System.out.println("Class Example");
    }
}

// Main class
public class Main {

    
    public static void main(String[] args) {

        // Strong Reference - by default
        Example ex = new Example();

        // Now object 'ex' is ready for garbage collection
        ex = null;

        // Creating ReferenceQueue
        ReferenceQueue<Example> referenceQueue = new ReferenceQueue<>();



        // Creating weak references

        // Using java.lang.WeakReference
        ex = new Example();
        WeakReference <Example> wr = new WeakReference<>(ex);

        Example ex1 = new Example();
        WeakReference <Example> wr1 = new WeakReference<>(ex1);

        Example ex2 = new Example();
        WeakReference <Example> wr2 = new WeakReference<>(ex2, referenceQueue);

        // Calling method from wr1
        wr1.get().print();

        // Using WeakHashMap<>
        WeakHashMap<Object, Example> weakHashMap = new WeakHashMap<>();
        // Adding Elements
        weakHashMap.put(0, ex);
        weakHashMap.put(1, ex1);
        weakHashMap.put(2, ex2);

        // Getting element from weakHashMap
        weakHashMap.get(1).print();

        // Creating soft references using java.lang.ref.SoftReference;
        SoftReference<Example> sf = new SoftReference<>(ex);
        // Clearing memory
        ex = null;
        // Getting element from sf
        sf.get().print();
        SoftReference<Example> sf1 = new SoftReference<>(ex2);
        // Getting element from sf1
        sf1.get().print();

        // Creating PhantomReferences for removing objects with garbage collector after calling finalize()
        ReferenceQueue<Example> referenceQueue1 = new ReferenceQueue<>();
        PhantomReference<Example> phr = new PhantomReference<>(ex1, referenceQueue1);
        // Getting elements from phr
        System.out.println(phr.get()); // Always returns null
    }
}