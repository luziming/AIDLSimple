/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: /Users/luziming/AndroidStudioProjects/SoulMate/client/src/main/aidl/com/jakelu/soulmate/IPlayAidlInterface.aidl
 */
package com.jakelu.soulmate;
// Declare any non-default types here with import statements

public interface IPlayAidlInterface extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.jakelu.soulmate.IPlayAidlInterface
{
private static final java.lang.String DESCRIPTOR = "com.jakelu.soulmate.IPlayAidlInterface";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.jakelu.soulmate.IPlayAidlInterface interface,
 * generating a proxy if needed.
 */
public static com.jakelu.soulmate.IPlayAidlInterface asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.jakelu.soulmate.IPlayAidlInterface))) {
return ((com.jakelu.soulmate.IPlayAidlInterface)iin);
}
return new com.jakelu.soulmate.IPlayAidlInterface.Stub.Proxy(obj);
}
@Override public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_playProgram:
{
data.enforceInterface(DESCRIPTOR);
com.jakelu.soulmate.Detail _arg0;
if ((0!=data.readInt())) {
_arg0 = com.jakelu.soulmate.Detail.CREATOR.createFromParcel(data);
}
else {
_arg0 = null;
}
this.playProgram(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_registeCallback:
{
data.enforceInterface(DESCRIPTOR);
com.jakelu.soulmate.IPlayCompleteCallback _arg0;
_arg0 = com.jakelu.soulmate.IPlayCompleteCallback.Stub.asInterface(data.readStrongBinder());
this.registeCallback(_arg0);
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.jakelu.soulmate.IPlayAidlInterface
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
@Override public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
/**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
@Override public void playProgram(com.jakelu.soulmate.Detail detail) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
if ((detail!=null)) {
_data.writeInt(1);
detail.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_playProgram, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void registeCallback(com.jakelu.soulmate.IPlayCompleteCallback callback) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((callback!=null))?(callback.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_registeCallback, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_playProgram = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_registeCallback = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
}
/**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
public void playProgram(com.jakelu.soulmate.Detail detail) throws android.os.RemoteException;
public void registeCallback(com.jakelu.soulmate.IPlayCompleteCallback callback) throws android.os.RemoteException;
}
