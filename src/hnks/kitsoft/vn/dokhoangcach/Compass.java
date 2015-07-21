package hnks.kitsoft.vn.dokhoangcach;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class Compass extends View{

	private float direction, direction_roll;
	  
	 public Compass(Context context) {
	  super(context);
	  // TODO Auto-generated constructor stub
	 }
	 
	 public Compass(Context context, AttributeSet attrs) {
	  super(context, attrs);
	  // TODO Auto-generated constructor stub
	 }
	 
	 public Compass(Context context, AttributeSet attrs, int defStyle) {
	  super(context, attrs, defStyle);
	  // TODO Auto-generated constructor stub
	 }
	 
	 @Override
	 protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
	  setMeasuredDimension(
	    MeasureSpec.getSize(widthMeasureSpec),
	    MeasureSpec.getSize(heightMeasureSpec));
	 }
	 
	 @SuppressLint("DrawAllocation")
	 @Override
	 protected void onDraw(Canvas canvas) {
	   
	  int w = getMeasuredWidth();
	  int h = getMeasuredHeight();
	  int r;
	  if(w > h){
	   r = h/2;
	  }else{
	   r = w/2;
	  }
	   
	  Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
	  paint.setStyle(Paint.Style.STROKE);
	  paint.setStrokeWidth(5);
	  paint.setColor(Color.BLUE);
	   
	  canvas.drawCircle(w/2, h/2, r, paint);
	  canvas.drawLine(
			    w/2,
			    h/2,
			    (float)(w/2 + r * Math.sin(0)),
			    (float)(h/2 - r * Math.cos(0)),
			    paint);
				   
	  paint.setColor(Color.RED);
//	  canvas.drawLine(
//	             w/2,
//	             h/2,
//	             (float)(w/2 + r * Math.sin(direction)),
//	             (float)(h/2 - r * Math.cos(direction)),
//	             paint);
	  
	  if (direction_roll>0){
	      canvas.drawLine(
	             w/2,
	             h/2,
	             (float)(w/2 + r * Math.sin(direction-90)),
	             (float)(h/2 - r * Math.cos(direction-90)),
	             paint);
	  } else {
		  canvas.drawLine(
		             w/2,
		             h/2,
		             (float)(w/2 + r * Math.sin(direction+90)),
		             (float)(h/2 - r * Math.cos(direction+90)),
		             paint);
	  }
	 
	 }
	  
	 public void update(float dir, float dir_roll){
	  direction = dir;
	  direction_roll = dir_roll;
	  invalidate();
	 }
	  

}