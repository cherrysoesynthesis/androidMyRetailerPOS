/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: D:\\AndroidStudioProjects\\projectsalmon2_harware_white_hub\\projectsalmon2_harware_white_hub\\src\\main\\aidl\\woyou\\aidlservice\\jiuiv5\\IYmodemSPI.aidl
 */
package com.dcs.myretailer.app;
public interface IYmodemSPI extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements IYmodemSPI
{
private static final String DESCRIPTOR = "IYmodemSPI";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an IYmodemSPI interface,
 * generating a proxy if needed.
 */
public static IYmodemSPI asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof IYmodemSPI))) {
return ((IYmodemSPI)iin);
}
return new IYmodemSPI.Stub.Proxy(obj);
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
case TRANSACTION_sendPercent:
{
data.enforceInterface(descriptor);
float _arg0;
_arg0 = data.readFloat();
this.sendPercent(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_sendFinish:
{
data.enforceInterface(descriptor);
int _arg0;
_arg0 = data.readInt();
this.sendFinish(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_onFinishYmodemDownload:
{
data.enforceInterface(descriptor);
boolean _arg0;
_arg0 = (0!=data.readInt());
String _arg1;
_arg1 = data.readString();
this.onFinishYmodemDownload(_arg0, _arg1);
reply.writeNoException();
return true;
}
default:
{
return super.onTransact(code, data, reply, flags);
}
}
}
private static class Proxy implements IYmodemSPI
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
	 * 一个指令的发送进度
	 * @param percent 发送百分比
	 */
@Override public void sendPercent(float percent) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeFloat(percent);
mRemote.transact(Stub.TRANSACTION_sendPercent, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
	 * message for send finish
	 * @param count send count number finish
	 */
@Override public void sendFinish(int count) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(count);
mRemote.transact(Stub.TRANSACTION_sendFinish, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
	 * ymodem download result
	 * @param flag			success flag
	 * @param msg			message
	 */
@Override public void onFinishYmodemDownload(boolean flag, String msg) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(((flag)?(1):(0)));
_data.writeString(msg);
mRemote.transact(Stub.TRANSACTION_onFinishYmodemDownload, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_sendPercent = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_sendFinish = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_onFinishYmodemDownload = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
}
/**
	 * 一个指令的发送进度
	 * @param percent 发送百分比
	 */
public void sendPercent(float percent) throws android.os.RemoteException;
/**
	 * message for send finish
	 * @param count send count number finish
	 */
public void sendFinish(int count) throws android.os.RemoteException;
/**
	 * ymodem download result
	 * @param flag			success flag
	 * @param msg			message
	 */
public void onFinishYmodemDownload(boolean flag, String msg) throws android.os.RemoteException;
}
