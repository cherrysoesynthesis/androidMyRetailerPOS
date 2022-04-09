/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: D:\\AndroidStudioProjects\\projectsalmon2_harware_white_hub\\projectsalmon2_harware_white_hub\\src\\main\\aidl\\woyou\\aidlservice\\jiuiv5\\ICallback.aidl
 */
package com.dcs.myretailer.app;
/**
 * Printer Service callback interface
 */
public interface ICallback extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements ICallback
{
private static final String DESCRIPTOR = "woyou.aidlservice.jiuiv5.ICallback";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an woyou.aidlservice.jiuiv5.ICallback interface,
 * generating a proxy if needed.
 */
public static ICallback asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof ICallback))) {
return ((ICallback)iin);
}
return (ICallback) new Proxy(obj);
}
@Override public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
String descriptor = DESCRIPTOR;
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(descriptor);
return true;
}
case TRANSACTION_onRunResult:
{
data.enforceInterface(descriptor);
boolean _arg0;
_arg0 = (0!=data.readInt());
this.onRunResult(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_onReturnString:
{
data.enforceInterface(descriptor);
String _arg0;
_arg0 = data.readString();
this.onReturnString(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_onRaiseException:
{
data.enforceInterface(descriptor);
int _arg0;
_arg0 = data.readInt();
String _arg1;
_arg1 = data.readString();
this.onRaiseException(_arg0, _arg1);
reply.writeNoException();
return true;
}
default:
{
return super.onTransact(code, data, reply, flags);
}
}
}
private static class Proxy implements ICallback
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
public String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
/**
	* return run result
	* @param isSuccess:	  true if printing success, false when printing failed
	*/
@Override public void onRunResult(boolean isSuccess) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(((isSuccess)?(1):(0)));
mRemote.transact(Stub.TRANSACTION_onRunResult, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
	* Return result
	* @param result:	result message
	*/
@Override public void onReturnString(String result) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(result);
mRemote.transact(Stub.TRANSACTION_onReturnString, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
	* Exception function
	* code:	Error Code
	* msg: Error Message
	*/
@Override public void onRaiseException(int code, String msg) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(code);
_data.writeString(msg);
mRemote.transact(Stub.TRANSACTION_onRaiseException, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_onRunResult = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_onReturnString = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_onRaiseException = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
}
/**
	* return run result
	* @param isSuccess:	  true if printing success, false when printing failed
	*/
public void onRunResult(boolean isSuccess) throws android.os.RemoteException;
/**
	* Return result
	* @param result:	result message
	*/
public void onReturnString(String result) throws android.os.RemoteException;
/**
	* Exception function
	* code:	Error Code
	* msg: Error Message
	*/
public void onRaiseException(int code, String msg) throws android.os.RemoteException;
}
