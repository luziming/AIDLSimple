// IPlayAidlInterface.aidl
package com.jakelu.soulmate;

//AIDL规定:非基本数据类型即使在统一包下也必须导包;
import com.jakelu.soulmate.Detail;
import com.jakelu.soulmate.IPlayCompleteCallback;
// Declare any non-default types here with import statements

interface IPlayAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void playProgram(in Detail detail);

    void registeCallback(in IPlayCompleteCallback callback);
}
