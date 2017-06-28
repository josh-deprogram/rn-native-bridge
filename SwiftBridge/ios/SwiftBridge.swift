//
//  SwiftBridge.swift
//  SwiftBridge
//
//  Created by joshua freeman on 27/9/16.
//  Copyright © 2016 Facebook. All rights reserved.
//

import Foundation
import AVFoundation

@objc(SwiftBridge)
class SwiftBridge: NSObject {
  
  var bridge: RCTBridge!  // this is synthesized
  var screenDim : Bool = false
  

  
   /*
   * Declare some constants accessible fromt he JS APP
   */
  @objc func constantsToExport() -> NSObject {
     return [
      "someVar": true,
       "x": 1,
       "y": 2,
       "z": "Arbitrary string"
     ]
  }



   /*
   * Create a method that recieves some props, and will then dispatch the results.
   */
  @objc func addEvent(name: String, location: String, date: NSNumber, callback: (NSObject) -> () ) -> Void {
    //  NSLog("Bridge: %@", self.bridge)
    NSLog("%@ %@ %@", name, location, date)

    let ret =  [
     "name": name,
     "location": location,
     "date" : date
     ]
    callback([ret])
    
    // Dispatch Event, this can be listened to on the JS side.
    self.bridge.eventDispatcher().sendAppEventWithName("EventReminder", body: ret)
  }


  /*
   * Sample Native Function call - Toggle the Flashlight.
   */
  @objc func toggleFlash() -> Void {
    let device = AVCaptureDevice.defaultDeviceWithMediaType(AVMediaTypeVideo)
    
    if (device.hasTorch) {
      do {
        try device.lockForConfiguration()
        if (device.torchMode == AVCaptureTorchMode.On) {
          device.torchMode = AVCaptureTorchMode.Off
        } else {
          do {
            try device.setTorchModeOnWithLevel(1.0)
          } catch {
            print(error)
          }
        }
        device.unlockForConfiguration()
      } catch {
        print(error)
      }
    }else{
      // If FlashLight is not avalible (iPAD) then change screen brightness instead.
      if(!screenDim){
        screenDim = true
        UIScreen.mainScreen().brightness = CGFloat(0.2)
      }else{
        screenDim = false
        UIScreen.mainScreen().brightness = CGFloat(1)
      }
    }
  }

  
}
