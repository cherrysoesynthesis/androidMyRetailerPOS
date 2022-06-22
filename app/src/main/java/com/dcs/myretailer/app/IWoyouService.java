/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: D:\\AndroidStudioProjects\\projectsalmon2_harware_white_hub\\projectsalmon2_harware_white_hub\\src\\main\\aidl\\woyou\\aidlservice\\jiuiv5\\IWoyouService.aidl
 */
package com.dcs.myretailer.app;
public interface IWoyouService extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements IWoyouService
{
private static final String DESCRIPTOR = "IWoyouService";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an IWoyouService interface,
 * generating a proxy if needed.
 */
public static IWoyouService asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof IWoyouService))) {
return ((IWoyouService)iin);
}
return (IWoyouService) new Proxy(obj);
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
case TRANSACTION_updateFirmware:
{
data.enforceInterface(descriptor);
byte[] _arg0;
_arg0 = data.createByteArray();
long _arg1;
_arg1 = data.readLong();
String _arg2;
_arg2 = data.readString();
IYmodemSPI _arg3;
_arg3 = IYmodemSPI.Stub.asInterface(data.readStrongBinder());
this.updateFirmware(_arg0, _arg1, _arg2, _arg3);
reply.writeNoException();
return true;
}
case TRANSACTION_getFirmwareStatus:
{
data.enforceInterface(descriptor);
int _result = this.getFirmwareStatus();
reply.writeNoException();
reply.writeInt(_result);
return true;
}
case TRANSACTION_getServiceVersion:
{
data.enforceInterface(descriptor);
String _result = this.getServiceVersion();
reply.writeNoException();
reply.writeString(_result);
return true;
}
case TRANSACTION_printerInit:
{
data.enforceInterface(descriptor);
ICallback _arg0;
_arg0 = ICallback.Stub.asInterface(data.readStrongBinder());
this.printerInit(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_printerSelfChecking:
{
data.enforceInterface(descriptor);
ICallback _arg0;
_arg0 = ICallback.Stub.asInterface(data.readStrongBinder());
this.printerSelfChecking(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_getPrinterSerialNo:
{
data.enforceInterface(descriptor);
String _result = this.getPrinterSerialNo();
reply.writeNoException();
reply.writeString(_result);
return true;
}
case TRANSACTION_getPrinterVersion:
{
data.enforceInterface(descriptor);
String _result = this.getPrinterVersion();
reply.writeNoException();
reply.writeString(_result);
return true;
}
case TRANSACTION_getPrinterModal:
{
data.enforceInterface(descriptor);
String _result = this.getPrinterModal();
reply.writeNoException();
reply.writeString(_result);
return true;
}
case TRANSACTION_getPrintedLength:
{
data.enforceInterface(descriptor);
ICallback _arg0;
_arg0 = ICallback.Stub.asInterface(data.readStrongBinder());
this.getPrintedLength(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_lineWrap:
{
data.enforceInterface(descriptor);
int _arg0;
_arg0 = data.readInt();
ICallback _arg1;
_arg1 = ICallback.Stub.asInterface(data.readStrongBinder());
this.lineWrap(_arg0, _arg1);
reply.writeNoException();
return true;
}
case TRANSACTION_sendRAWData:
{
data.enforceInterface(descriptor);
byte[] _arg0;
_arg0 = data.createByteArray();
ICallback _arg1;
_arg1 = ICallback.Stub.asInterface(data.readStrongBinder());
this.sendRAWData(_arg0, _arg1);
reply.writeNoException();
return true;
}
case TRANSACTION_setAlignment:
{
data.enforceInterface(descriptor);
int _arg0;
_arg0 = data.readInt();
ICallback _arg1;
_arg1 = ICallback.Stub.asInterface(data.readStrongBinder());
this.setAlignment(_arg0, _arg1);
reply.writeNoException();
return true;
}
case TRANSACTION_setFontName:
{
data.enforceInterface(descriptor);
String _arg0;
_arg0 = data.readString();
ICallback _arg1;
_arg1 = ICallback.Stub.asInterface(data.readStrongBinder());
this.setFontName(_arg0, _arg1);
reply.writeNoException();
return true;
}
case TRANSACTION_setFontSize:
{
data.enforceInterface(descriptor);
float _arg0;
_arg0 = data.readFloat();
ICallback _arg1;
_arg1 = ICallback.Stub.asInterface(data.readStrongBinder());
this.setFontSize(_arg0, _arg1);
reply.writeNoException();
return true;
}
case TRANSACTION_printText:
{
data.enforceInterface(descriptor);
String _arg0;
_arg0 = data.readString();
ICallback _arg1;
_arg1 = ICallback.Stub.asInterface(data.readStrongBinder());
this.printText(_arg0, _arg1);
reply.writeNoException();
return true;
}
case TRANSACTION_printTextWithFont:
{
data.enforceInterface(descriptor);
String _arg0;
_arg0 = data.readString();
String _arg1;
_arg1 = data.readString();
float _arg2;
_arg2 = data.readFloat();
ICallback _arg3;
_arg3 = ICallback.Stub.asInterface(data.readStrongBinder());
this.printTextWithFont(_arg0, _arg1, _arg2, _arg3);
reply.writeNoException();
return true;
}
case TRANSACTION_printColumnsText:
{
data.enforceInterface(descriptor);
String[] _arg0;
_arg0 = data.createStringArray();
int[] _arg1;
_arg1 = data.createIntArray();
int[] _arg2;
_arg2 = data.createIntArray();
ICallback _arg3;
_arg3 = ICallback.Stub.asInterface(data.readStrongBinder());
this.printColumnsText(_arg0, _arg1, _arg2, _arg3);
reply.writeNoException();
return true;
}
case TRANSACTION_printBitmap:
{
data.enforceInterface(descriptor);
android.graphics.Bitmap _arg0;
if ((0!=data.readInt())) {
_arg0 = android.graphics.Bitmap.CREATOR.createFromParcel(data);
}
else {
_arg0 = null;
}
ICallback _arg1;
_arg1 = ICallback.Stub.asInterface(data.readStrongBinder());
this.printBitmap(_arg0, _arg1);
reply.writeNoException();
return true;
}
case TRANSACTION_printBarCode:
{
data.enforceInterface(descriptor);
String _arg0;
_arg0 = data.readString();
int _arg1;
_arg1 = data.readInt();
int _arg2;
_arg2 = data.readInt();
int _arg3;
_arg3 = data.readInt();
int _arg4;
_arg4 = data.readInt();
ICallback _arg5;
_arg5 = ICallback.Stub.asInterface(data.readStrongBinder());
this.printBarCode(_arg0, _arg1, _arg2, _arg3, _arg4, _arg5);
reply.writeNoException();
return true;
}
case TRANSACTION_printQRCode:
{
data.enforceInterface(descriptor);
String _arg0;
_arg0 = data.readString();
int _arg1;
_arg1 = data.readInt();
int _arg2;
_arg2 = data.readInt();
ICallback _arg3;
_arg3 = ICallback.Stub.asInterface(data.readStrongBinder());
this.printQRCode(_arg0, _arg1, _arg2, _arg3);
reply.writeNoException();
return true;
}
case TRANSACTION_printOriginalText:
{
data.enforceInterface(descriptor);
String _arg0;
_arg0 = data.readString();
ICallback _arg1;
_arg1 = ICallback.Stub.asInterface(data.readStrongBinder());
this.printOriginalText(_arg0, _arg1);
reply.writeNoException();
return true;
}
default:
{
return super.onTransact(code, data, reply, flags);
}
}
}
private static class Proxy implements IWoyouService
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
	* Upgrade Printer Firmware (Only for developer mode)
	* @param buffer			
	* @param size
	* @param filename
	* @param iapInterface
	*/
@Override public void updateFirmware(byte[] buffer, long size, String filename, IYmodemSPI iapInterface) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeByteArray(buffer);
_data.writeLong(size);
_data.writeString(filename);
_data.writeStrongBinder((((iapInterface!=null))?(iapInterface.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_updateFirmware, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public int getFirmwareStatus() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getFirmwareStatus, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public String getServiceVersion() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
String _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getServiceVersion, _data, _reply, 0);
_reply.readException();
_result = _reply.readString();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
	 * Initializing Printer, clear all buffer data
	 * 
	 * @param callback 
	 * @return
	 */
@Override public void printerInit(ICallback callback) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((callback!=null))?(callback.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_printerInit, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
	* Printer self test method
	* Prints self test page
	* @param callback 鍥炶皟
	*/
@Override public void printerSelfChecking(ICallback callback) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((callback!=null))?(callback.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_printerSelfChecking, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public String getPrinterSerialNo() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
String _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getPrinterSerialNo, _data, _reply, 0);
_reply.readException();
_result = _reply.readString();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public String getPrinterVersion() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
String _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getPrinterVersion, _data, _reply, 0);
_reply.readException();
_result = _reply.readString();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public String getPrinterModal() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
String _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getPrinterModal, _data, _reply, 0);
_reply.readException();
_result = _reply.readString();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
	* Get Print head length
	*/
@Override public void getPrintedLength(ICallback callback) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((callback!=null))?(callback.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_getPrintedLength, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
	 * 鎵撳嵃鏈鸿蛋绾�(寮哄埗鎹㈣锛岀粨鏉熶箣鍓嶇殑鎵撳嵃鍐呭鍚庤蛋绾竛琛�)
	 * @param n:	璧扮焊琛屾暟
	 * @param callback  缁撴灉鍥炶皟
	 * @return
	 */
@Override public void lineWrap(int n, ICallback callback) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(n);
_data.writeStrongBinder((((callback!=null))?(callback.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_lineWrap, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
	* Send Raw Data
	* @param data	        鎸囦护
	* @param callback  缁撴灉鍥炶皟
	*/
@Override public void sendRAWData(byte[] data, ICallback callback) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeByteArray(data);
_data.writeStrongBinder((((callback!=null))?(callback.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_sendRAWData, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
	* 璁剧疆瀵归綈妯″紡锛屽涔嬪悗鎵撳嵃鏈夊奖鍝嶏紝闄ら潪鍒濆鍖�
	* @param alignment:	瀵归綈鏂瑰紡 0--灞呭乏 , 1--灞呬腑, 2--灞呭彸
	* @param callback  缁撴灉鍥炶皟
	*/
@Override public void setAlignment(int alignment, ICallback callback) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(alignment);
_data.writeStrongBinder((((callback!=null))?(callback.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_setAlignment, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
	* 璁剧疆鎵撳嵃瀛椾綋, 瀵逛箣鍚庢墦鍗版湁褰卞搷锛岄櫎闈炲垵濮嬪寲
	* (鐩墠鍙敮鎸佷竴绉嶅瓧浣�"gh"锛実h鏄竴绉嶇瓑瀹戒腑鏂囧瓧浣擄紝涔嬪悗浼氭彁渚涙洿澶氬瓧浣撻�夋嫨)
	* @param typeface:		瀛椾綋鍚嶇О
	*/
@Override public void setFontName(String typeface, ICallback callback) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(typeface);
_data.writeStrongBinder((((callback!=null))?(callback.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_setFontName, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
	* 璁剧疆瀛椾綋澶у皬, 瀵逛箣鍚庢墦鍗版湁褰卞搷锛岄櫎闈炲垵濮嬪寲
	* 娉ㄦ剰锛氬瓧浣撳ぇ灏忔槸瓒呭嚭鏍囧噯鍥介檯鎸囦护鐨勬墦鍗版柟寮忥紝
	* 璋冩暣瀛椾綋澶у皬浼氬奖鍝嶅瓧绗﹀搴︼紝姣忚瀛楃鏁伴噺涔熶細闅忎箣鏀瑰彉锛�
	* 鍥犳鎸夌瓑瀹藉瓧浣撳舰鎴愮殑鎺掔増鍙兘浼氶敊涔�
	* @param fontsize:	瀛椾綋澶у皬
	*/
@Override public void setFontSize(float fontsize, ICallback callback) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeFloat(fontsize);
_data.writeStrongBinder((((callback!=null))?(callback.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_setFontSize, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
	* 鎵撳嵃鏂囧瓧锛屾枃瀛楀搴︽弧涓�琛岃嚜鍔ㄦ崲琛屾帓鐗堬紝涓嶆弧涓�鏁磋涓嶆墦鍗伴櫎闈炲己鍒舵崲琛�
	* @param text:	瑕佹墦鍗扮殑鏂囧瓧瀛楃涓�
	*/
@Override public void printText(String text, ICallback callback) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(text);
_data.writeStrongBinder((((callback!=null))?(callback.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_printText, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
	* 鎵撳嵃鎸囧畾瀛椾綋鐨勬枃鏈紝瀛椾綋璁剧疆鍙鏈鏈夋晥
	* @param text:			瑕佹墦鍗版枃瀛�
	* @param typeface:		瀛椾綋鍚嶇О锛堢洰鍓嶅彧鏀寔"gh"瀛椾綋锛�
	* @param fontsize:		瀛椾綋澶у皬	
	*/
@Override public void printTextWithFont(String text, String typeface, float fontsize, ICallback callback) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(text);
_data.writeString(typeface);
_data.writeFloat(fontsize);
_data.writeStrongBinder((((callback!=null))?(callback.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_printTextWithFont, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
	* 鎵撳嵃琛ㄦ牸鐨勪竴琛岋紝鍙互鎸囧畾鍒楀銆佸榻愭柟寮�
	* @param colsTextArr   鍚勫垪鏂囨湰瀛楃涓叉暟缁�
	* @param colsWidthArr  鍚勫垪瀹藉害鏁扮粍(浠ヨ嫳鏂囧瓧绗﹁绠�, 姣忎釜涓枃瀛楃鍗犱袱涓嫳鏂囧瓧绗�, 姣忎釜瀹藉害澶т簬0)
	* @param colsAlign	        鍚勫垪瀵归綈鏂瑰紡(0灞呭乏, 1灞呬腑, 2灞呭彸)
	* 澶囨敞: 涓変釜鍙傛暟鐨勬暟缁勯暱搴﹀簲璇ヤ竴鑷�, 濡傛灉colsText[i]鐨勫搴﹀ぇ浜巆olsWidth[i], 鍒欐枃鏈崲琛�
	*/
@Override public void printColumnsText(String[] colsTextArr, int[] colsWidthArr, int[] colsAlign, ICallback callback) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStringArray(colsTextArr);
_data.writeIntArray(colsWidthArr);
_data.writeIntArray(colsAlign);
_data.writeStrongBinder((((callback!=null))?(callback.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_printColumnsText, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
	* Prints bitmap
	* @param bitmap: bitmap size cannot be more than 384pixel width otherwise callback will be thrown
	*/
@Override public void printBitmap(android.graphics.Bitmap bitmap, ICallback callback) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
if ((bitmap!=null)) {
_data.writeInt(1);
bitmap.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
_data.writeStrongBinder((((callback!=null))?(callback.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_printBitmap, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
	* 鎵撳嵃涓�缁存潯鐮�
	* @param data: 		鏉＄爜鏁版嵁
	* @param symbology: 	鏉＄爜绫诲瀷
	*    0 -- UPC-A锛�
	*    1 -- UPC-E锛�
	*    2 -- JAN13(EAN13)锛�
	*    3 -- JAN8(EAN8)锛�
	*    4 -- CODE39锛�
	*    5 -- ITF锛�
	*    6 -- CODABAR锛�
	*    7 -- CODE93锛�
	*    8 -- CODE128
	* @param height: 		鏉＄爜楂樺害, 鍙栧��1鍒�255, 榛樿162
	* @param width: 		鏉＄爜瀹藉害, 鍙栧��2鑷�6, 榛樿2
	* @param textposition:	鏂囧瓧浣嶇疆 0--涓嶆墦鍗版枃瀛�, 1--鏂囧瓧鍦ㄦ潯鐮佷笂鏂�, 2--鏂囧瓧鍦ㄦ潯鐮佷笅鏂�, 3--鏉＄爜涓婁笅鏂瑰潎鎵撳嵃
	*/
@Override public void printBarCode(String data, int symbology, int height, int width, int textposition, ICallback callback) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(data);
_data.writeInt(symbology);
_data.writeInt(height);
_data.writeInt(width);
_data.writeInt(textposition);
_data.writeStrongBinder((((callback!=null))?(callback.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_printBarCode, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
	* 鎵撳嵃浜岀淮鏉＄爜
	* @param data:			浜岀淮鐮佹暟鎹�
	* @param modulesize:	浜岀淮鐮佸潡澶у皬(鍗曚綅:鐐�, 鍙栧�� 1 鑷� 16 )
	* @param errorlevel:	浜岀淮鐮佺籂閿欑瓑绾�(0 鑷� 3)锛�
	*                0 -- 绾犻敊绾у埆L ( 7%)锛�
	*                1 -- 绾犻敊绾у埆M (15%)锛�
	*                2 -- 绾犻敊绾у埆Q (25%)锛�
	*                3 -- 绾犻敊绾у埆H (30%) 
	*/
@Override public void printQRCode(String data, int modulesize, int errorlevel, ICallback callback) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(data);
_data.writeInt(modulesize);
_data.writeInt(errorlevel);
_data.writeStrongBinder((((callback!=null))?(callback.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_printQRCode, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
	* 鎵撳嵃鏂囧瓧锛屾枃瀛楀搴︽弧涓�琛岃嚜鍔ㄦ崲琛屾帓鐗堬紝涓嶆弧涓�鏁磋涓嶆墦鍗伴櫎闈炲己鍒舵崲琛�
	* 鏂囧瓧鎸夌煝閲忔枃瀛楀搴﹀師鏍疯緭鍑猴紝鍗虫瘡涓瓧绗︿笉绛夊
	* @param text:	瑕佹墦鍗扮殑鏂囧瓧瀛楃涓�
	* Ver 1.7.6涓鍔�
	*/
@Override public void printOriginalText(String text, ICallback callback) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(text);
_data.writeStrongBinder((((callback!=null))?(callback.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_printOriginalText, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_updateFirmware = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_getFirmwareStatus = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_getServiceVersion = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
static final int TRANSACTION_printerInit = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
static final int TRANSACTION_printerSelfChecking = (android.os.IBinder.FIRST_CALL_TRANSACTION + 4);
static final int TRANSACTION_getPrinterSerialNo = (android.os.IBinder.FIRST_CALL_TRANSACTION + 5);
static final int TRANSACTION_getPrinterVersion = (android.os.IBinder.FIRST_CALL_TRANSACTION + 6);
static final int TRANSACTION_getPrinterModal = (android.os.IBinder.FIRST_CALL_TRANSACTION + 7);
static final int TRANSACTION_getPrintedLength = (android.os.IBinder.FIRST_CALL_TRANSACTION + 8);
static final int TRANSACTION_lineWrap = (android.os.IBinder.FIRST_CALL_TRANSACTION + 9);
static final int TRANSACTION_sendRAWData = (android.os.IBinder.FIRST_CALL_TRANSACTION + 10);
static final int TRANSACTION_setAlignment = (android.os.IBinder.FIRST_CALL_TRANSACTION + 11);
static final int TRANSACTION_setFontName = (android.os.IBinder.FIRST_CALL_TRANSACTION + 12);
static final int TRANSACTION_setFontSize = (android.os.IBinder.FIRST_CALL_TRANSACTION + 13);
static final int TRANSACTION_printText = (android.os.IBinder.FIRST_CALL_TRANSACTION + 14);
static final int TRANSACTION_printTextWithFont = (android.os.IBinder.FIRST_CALL_TRANSACTION + 15);
static final int TRANSACTION_printColumnsText = (android.os.IBinder.FIRST_CALL_TRANSACTION + 16);
static final int TRANSACTION_printBitmap = (android.os.IBinder.FIRST_CALL_TRANSACTION + 17);
static final int TRANSACTION_printBarCode = (android.os.IBinder.FIRST_CALL_TRANSACTION + 18);
static final int TRANSACTION_printQRCode = (android.os.IBinder.FIRST_CALL_TRANSACTION + 19);
static final int TRANSACTION_printOriginalText = (android.os.IBinder.FIRST_CALL_TRANSACTION + 20);
}
/**
	* Upgrade Printer Firmware (Only for developer mode)
	* @param buffer			
	* @param size
	* @param filename
	* @param iapInterface
	*/
public void updateFirmware(byte[] buffer, long size, String filename, IYmodemSPI iapInterface) throws android.os.RemoteException;
public int getFirmwareStatus() throws android.os.RemoteException;
public String getServiceVersion() throws android.os.RemoteException;
/**
	 * Initializing Printer, clear all buffer data
	 * 
	 * @param callback 
	 * @return
	 */
public void printerInit(ICallback callback) throws android.os.RemoteException;
/**
	* Printer self test method
	* Prints self test page
	* @param callback 鍥炶皟
	*/
public void printerSelfChecking(ICallback callback) throws android.os.RemoteException;
public String getPrinterSerialNo() throws android.os.RemoteException;
public String getPrinterVersion() throws android.os.RemoteException;
public String getPrinterModal() throws android.os.RemoteException;
/**
	* Get Print head length
	*/
public void getPrintedLength(ICallback callback) throws android.os.RemoteException;
/**
	 * 鎵撳嵃鏈鸿蛋绾�(寮哄埗鎹㈣锛岀粨鏉熶箣鍓嶇殑鎵撳嵃鍐呭鍚庤蛋绾竛琛�)
	 * @param n:	璧扮焊琛屾暟
	 * @param callback  缁撴灉鍥炶皟
	 * @return
	 */
public void lineWrap(int n, ICallback callback) throws android.os.RemoteException;
/**
	* Send Raw Data
	* @param data	        鎸囦护
	* @param callback  缁撴灉鍥炶皟
	*/
public void sendRAWData(byte[] data, ICallback callback) throws android.os.RemoteException;
/**
	* 璁剧疆瀵归綈妯″紡锛屽涔嬪悗鎵撳嵃鏈夊奖鍝嶏紝闄ら潪鍒濆鍖�
	* @param alignment:	瀵归綈鏂瑰紡 0--灞呭乏 , 1--灞呬腑, 2--灞呭彸
	* @param callback  缁撴灉鍥炶皟
	*/
public void setAlignment(int alignment, ICallback callback) throws android.os.RemoteException;
/**
	* 璁剧疆鎵撳嵃瀛椾綋, 瀵逛箣鍚庢墦鍗版湁褰卞搷锛岄櫎闈炲垵濮嬪寲
	* (鐩墠鍙敮鎸佷竴绉嶅瓧浣�"gh"锛実h鏄竴绉嶇瓑瀹戒腑鏂囧瓧浣擄紝涔嬪悗浼氭彁渚涙洿澶氬瓧浣撻�夋嫨)
	* @param typeface:		瀛椾綋鍚嶇О
	*/
public void setFontName(String typeface, ICallback callback) throws android.os.RemoteException;
/**
	* 璁剧疆瀛椾綋澶у皬, 瀵逛箣鍚庢墦鍗版湁褰卞搷锛岄櫎闈炲垵濮嬪寲
	* 娉ㄦ剰锛氬瓧浣撳ぇ灏忔槸瓒呭嚭鏍囧噯鍥介檯鎸囦护鐨勬墦鍗版柟寮忥紝
	* 璋冩暣瀛椾綋澶у皬浼氬奖鍝嶅瓧绗﹀搴︼紝姣忚瀛楃鏁伴噺涔熶細闅忎箣鏀瑰彉锛�
	* 鍥犳鎸夌瓑瀹藉瓧浣撳舰鎴愮殑鎺掔増鍙兘浼氶敊涔�
	* @param fontsize:	瀛椾綋澶у皬
	*/
public void setFontSize(float fontsize, ICallback callback) throws android.os.RemoteException;
/**
	* 鎵撳嵃鏂囧瓧锛屾枃瀛楀搴︽弧涓�琛岃嚜鍔ㄦ崲琛屾帓鐗堬紝涓嶆弧涓�鏁磋涓嶆墦鍗伴櫎闈炲己鍒舵崲琛�
	* @param text:	瑕佹墦鍗扮殑鏂囧瓧瀛楃涓�
	*/
public void printText(String text, ICallback callback) throws android.os.RemoteException;
/**
	* 鎵撳嵃鎸囧畾瀛椾綋鐨勬枃鏈紝瀛椾綋璁剧疆鍙鏈鏈夋晥
	* @param text:			瑕佹墦鍗版枃瀛�
	* @param typeface:		瀛椾綋鍚嶇О锛堢洰鍓嶅彧鏀寔"gh"瀛椾綋锛�
	* @param fontsize:		瀛椾綋澶у皬	
	*/
public void printTextWithFont(String text, String typeface, float fontsize, ICallback callback) throws android.os.RemoteException;
/**
	* 鎵撳嵃琛ㄦ牸鐨勪竴琛岋紝鍙互鎸囧畾鍒楀銆佸榻愭柟寮�
	* @param colsTextArr   鍚勫垪鏂囨湰瀛楃涓叉暟缁�
	* @param colsWidthArr  鍚勫垪瀹藉害鏁扮粍(浠ヨ嫳鏂囧瓧绗﹁绠�, 姣忎釜涓枃瀛楃鍗犱袱涓嫳鏂囧瓧绗�, 姣忎釜瀹藉害澶т簬0)
	* @param colsAlign	        鍚勫垪瀵归綈鏂瑰紡(0灞呭乏, 1灞呬腑, 2灞呭彸)
	* 澶囨敞: 涓変釜鍙傛暟鐨勬暟缁勯暱搴﹀簲璇ヤ竴鑷�, 濡傛灉colsText[i]鐨勫搴﹀ぇ浜巆olsWidth[i], 鍒欐枃鏈崲琛�
	*/
public void printColumnsText(String[] colsTextArr, int[] colsWidthArr, int[] colsAlign, ICallback callback) throws android.os.RemoteException;
/**
	* Prints bitmap
	* @param bitmap: bitmap size cannot be more than 384pixel width otherwise callback will be thrown
	*/
public void printBitmap(android.graphics.Bitmap bitmap, ICallback callback) throws android.os.RemoteException;
/**
	* 鎵撳嵃涓�缁存潯鐮�
	* @param data: 		鏉＄爜鏁版嵁
	* @param symbology: 	鏉＄爜绫诲瀷
	*    0 -- UPC-A锛�
	*    1 -- UPC-E锛�
	*    2 -- JAN13(EAN13)锛�
	*    3 -- JAN8(EAN8)锛�
	*    4 -- CODE39锛�
	*    5 -- ITF锛�
	*    6 -- CODABAR锛�
	*    7 -- CODE93锛�
	*    8 -- CODE128
	* @param height: 		鏉＄爜楂樺害, 鍙栧��1鍒�255, 榛樿162
	* @param width: 		鏉＄爜瀹藉害, 鍙栧��2鑷�6, 榛樿2
	* @param textposition:	鏂囧瓧浣嶇疆 0--涓嶆墦鍗版枃瀛�, 1--鏂囧瓧鍦ㄦ潯鐮佷笂鏂�, 2--鏂囧瓧鍦ㄦ潯鐮佷笅鏂�, 3--鏉＄爜涓婁笅鏂瑰潎鎵撳嵃
	*/
public void printBarCode(String data, int symbology, int height, int width, int textposition, ICallback callback) throws android.os.RemoteException;
/**
	* 鎵撳嵃浜岀淮鏉＄爜
	* @param data:			浜岀淮鐮佹暟鎹�
	* @param modulesize:	浜岀淮鐮佸潡澶у皬(鍗曚綅:鐐�, 鍙栧�� 1 鑷� 16 )
	* @param errorlevel:	浜岀淮鐮佺籂閿欑瓑绾�(0 鑷� 3)锛�
	*                0 -- 绾犻敊绾у埆L ( 7%)锛�
	*                1 -- 绾犻敊绾у埆M (15%)锛�
	*                2 -- 绾犻敊绾у埆Q (25%)锛�
	*                3 -- 绾犻敊绾у埆H (30%) 
	*/
public void printQRCode(String data, int modulesize, int errorlevel, ICallback callback) throws android.os.RemoteException;
/**
	* 鎵撳嵃鏂囧瓧锛屾枃瀛楀搴︽弧涓�琛岃嚜鍔ㄦ崲琛屾帓鐗堬紝涓嶆弧涓�鏁磋涓嶆墦鍗伴櫎闈炲己鍒舵崲琛�
	* 鏂囧瓧鎸夌煝閲忔枃瀛楀搴﹀師鏍疯緭鍑猴紝鍗虫瘡涓瓧绗︿笉绛夊
	* @param text:	瑕佹墦鍗扮殑鏂囧瓧瀛楃涓�
	* Ver 1.7.6涓鍔�
	*/
public void printOriginalText(String text, ICallback callback) throws android.os.RemoteException;
}
