///* MIT License
//
//Copyright (c) 2017 icebahamut
//
//Permission is hereby granted, free of charge, to any person obtaining a copy
//of this software and associated documentation files (the "Software"), to deal
//in the Software without restriction, including without limitation the rights
//to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
//copies of the Software, and to permit persons to whom the Software is
//furnished to do so, subject to the following conditions:
//
//The above copyright notice and this permission notice shall be included in all
//copies or substantial portions of the Software.
//
//THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
//IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
//FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
//AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
//LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
//OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
//SOFTWARE. */
//
//package com.dcs.myretailer.app;
////
////import android.content.Context;
////import android.graphics.Bitmap;
////import android.graphics.Canvas;
////import android.util.AttributeSet;
////import android.view.View;
//
//import android.content.Context;
//import android.graphics.Bitmap;
//import android.graphics.Canvas;
//import android.util.AttributeSet;
//import android.view.View;
//
//public class IconViewer extends View {
//
//	private Bitmap imgicon = null;
//	public IconViewer(Context context) {
//		super(context);
//	}
//
//	public IconViewer(Context context, AttributeSet attrs) {
//		super(context, attrs);
//	}
//
//	public void setIcon(Bitmap b){
//		imgicon = b;
//		invalidate();
//	}
//
//	public Bitmap getIcon(){
//		return imgicon;
//	}
//
//	@Override
//	public void onDraw(Canvas c){
//		if(c.getWidth()==0 || c.getHeight()==0 || imgicon==null){
//			return;
//		}
//
//		float scale = 1f;
//
//
//		if(c.getWidth()>c.getHeight()){
//			scale = (float)c.getHeight()/(float)imgicon.getHeight();
//		}else{
//			scale = (float)c.getWidth()/(float)imgicon.getWidth();
//		}
//		c.save();
//		c.scale(scale, scale);
//		c.drawBitmap(imgicon, 0, 0, null);
//		c.restore();
//	}
//
//}
