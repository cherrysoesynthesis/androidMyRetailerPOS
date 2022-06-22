/*
 * ===========================================================================================
 * = COPYRIGHT
 *          PAX Computer Technology(Shenzhen) CO., LTD PROPRIETARY INFORMATION
 *   This software is supplied under the terms of a license agreement or nondisclosure
 *   agreement with PAX Computer Technology(Shenzhen) CO., LTD and may not be copied or
 *   disclosed except in accordance with the terms in that agreement.
 *     Copyright (C) 2019-? PAX Computer Technology(Shenzhen) CO., LTD All rights reserved.
 * Description: // Detail description about the function of this module,
 *             // interfaces with the other modules, and dependencies.
 * Revision History:
 * Date                  Author	                 Action
 * 20190108  	         Kim.L                   Create
 * ===========================================================================================
 */
package com.pax.commonlib.utils.impl;

import com.pax.commonlib.utils.LogUtils;
import com.pax.commonlib.utils.convert.IConvert;
import com.pax.gl.utils.impl.Convert;

public class ConverterGLImp implements IConvert {
    private static ConverterGLImp converterImp;

    public static IConvert getConvert() {
        if (converterImp == null) {
            converterImp = new ConverterGLImp();
        }
        return converterImp;
    }

    public String bcdToStr(byte[] b) {
        return Convert.bcdToStr(b);
    }

    @Override
    public byte[] strToBcdPaddingLeft(String str) {
        LogUtils.d("ConvertHelper", "strToBcdPaddingLeft：" + str);
        return strToBcd(str, EPaddingPosition.PADDING_LEFT);
    }

    @Override
    public byte[] strToBcdPaddingRight(String str) {
        LogUtils.d("ConvertHelper", "strToBcdPaddingRight：" + str);
        return strToBcd(str, EPaddingPosition.PADDING_RIGHT);
    }

    public byte[] strToBcd(String str, EPaddingPosition paddingPosition) {
        if (paddingPosition == EPaddingPosition.PADDING_RIGHT)
            return Convert.strToBcd(str, Convert.EPaddingPosition.PADDING_RIGHT);
        else {
            return Convert.strToBcd(str, Convert.EPaddingPosition.PADDING_LEFT);
        }
    }

    public void longToByteArray(long l, byte[] to, int offset, EEndian endian) {
        if (endian == EEndian.BIG_ENDIAN)
            Convert.longToByteArray(l, to, offset, Convert.EEndian.BIG_ENDIAN);
        else {
            Convert.longToByteArray(l, to, offset, Convert.EEndian.LITTLE_ENDIAN);
        }
    }

    public byte[] longToByteArray(long l, EEndian endian) {
        if (endian == EEndian.BIG_ENDIAN)
            return Convert.longToByteArray(l, Convert.EEndian.BIG_ENDIAN);
        else {
            return Convert.longToByteArray(l, Convert.EEndian.LITTLE_ENDIAN);
        }
    }

    public void intToByteArray(int paramInt1, byte[] paramArrayOfByte, int paramInt2, EEndian paramEEndian) {
        if (paramEEndian == EEndian.BIG_ENDIAN)
            Convert.intToByteArray(paramInt1, paramArrayOfByte, paramInt2, Convert.EEndian.BIG_ENDIAN);
        else {
            Convert.intToByteArray(paramInt1, paramArrayOfByte, paramInt2, Convert.EEndian.LITTLE_ENDIAN);
        }
    }

    public byte[] intToByteArray(int paramInt, EEndian paramEEndian) {
        if (paramEEndian == EEndian.BIG_ENDIAN)
            return Convert.intToByteArray(paramInt, Convert.EEndian.BIG_ENDIAN);
        else {
            return Convert.intToByteArray(paramInt, Convert.EEndian.LITTLE_ENDIAN);
        }
    }

    public void shortToByteArray(short paramShort, byte[] paramArrayOfByte, int paramInt, EEndian paramEEndian) {
        if (paramEEndian == EEndian.BIG_ENDIAN)
            Convert.shortToByteArray(paramShort, paramArrayOfByte, paramInt, Convert.EEndian.BIG_ENDIAN);
        else {
            Convert.shortToByteArray(paramShort, paramArrayOfByte, paramInt, Convert.EEndian.LITTLE_ENDIAN);
        }
    }

    public byte[] shortToByteArray(short paramShort, EEndian paramEEndian) {
        if (paramEEndian == EEndian.BIG_ENDIAN)
            return Convert.shortToByteArray(paramShort, Convert.EEndian.BIG_ENDIAN);
        else {
            return Convert.shortToByteArray(paramShort, Convert.EEndian.LITTLE_ENDIAN);
        }
    }

    public long longFromByteArray(byte[] paramArrayOfByte, int paramInt, EEndian paramEEndian) {
        if (paramEEndian == EEndian.BIG_ENDIAN)
            return Convert.longFromByteArray(paramArrayOfByte, paramInt, Convert.EEndian.BIG_ENDIAN);
        else {
            return Convert.longFromByteArray(paramArrayOfByte, paramInt, Convert.EEndian.LITTLE_ENDIAN);
        }
    }

    public int intFromByteArray(byte[] paramArrayOfByte, int paramInt, EEndian paramEEndian) {
        if (paramEEndian == EEndian.BIG_ENDIAN)
            return Convert.intFromByteArray(paramArrayOfByte, paramInt, Convert.EEndian.BIG_ENDIAN);
        else {
            return Convert.intFromByteArray(paramArrayOfByte, paramInt, Convert.EEndian.LITTLE_ENDIAN);
        }
    }

    public short shortFromByteArray(byte[] paramArrayOfByte, int paramInt, EEndian paramEEndian) {
        if (paramEEndian == EEndian.BIG_ENDIAN)
            return Convert.shortFromByteArray(paramArrayOfByte, paramInt, Convert.EEndian.BIG_ENDIAN);
        else {
            return Convert.shortFromByteArray(paramArrayOfByte, paramInt, Convert.EEndian.LITTLE_ENDIAN);
        }
    }

    public String stringPadding(String paramString, char paramChar, long paramLong, EPaddingPosition paramEPaddingPosition) {
        if (paramEPaddingPosition == EPaddingPosition.PADDING_LEFT)
            return Convert.stringPadding(paramString, paramChar, paramLong, Convert.EPaddingPosition.PADDING_LEFT);
        else {
            return Convert.stringPadding(paramString, paramChar, paramLong, Convert.EPaddingPosition.PADDING_RIGHT);
        }
    }

    public boolean isByteArrayValueSame(byte[] paramArrayOfByte1, int paramInt1, byte[] paramArrayOfByte2, int paramInt2, int paramInt3) {
        if ((paramArrayOfByte1 == null) || (paramArrayOfByte2 == null)) {
            return false;
        }

        if ((paramInt1 + paramInt3 > paramArrayOfByte1.length) || (paramInt2 + paramInt3 > paramArrayOfByte2.length)) {
            return false;
        }

        for (int i = 0; i < paramInt3; i++) {
            if (paramArrayOfByte1[(paramInt1 + i)] != paramArrayOfByte2[(paramInt2 + i)]) {
                return false;
            }
        }

        return true;
    }
}
