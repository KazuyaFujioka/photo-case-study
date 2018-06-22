package com.casestudy.infrastructure.datasource.adjustment;

import com.casestudy.domain.model.adjustment.AdjustmentSize;

import org.bytedeco.javacpp.opencv_core.Mat;
import org.bytedeco.javacpp.opencv_core.Size;

interface Adjustment {
   Size size();
   Mat roi();

   static Adjustment create(Mat destination,
                            AdjustmentSize size,
                            Ratio request, Ratio original) {

      if(original.isLarge(request))
         return new AdjustmentHeight(destination, size, request, original);

      return new AdjustmentWidth(destination, size, request, original);
   }
}