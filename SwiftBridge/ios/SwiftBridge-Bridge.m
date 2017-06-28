//
//  The Swift Bridge
//

//#import

#import "RCTBridgeModule.h"

@interface RCT_EXTERN_MODULE(SwiftBridge, NSObject)

RCT_EXTERN_METHOD(addEvent:(NSString *)name location:(NSString *)location date:(nonnull NSNumber *)date callback: (RCTResponseSenderBlock)callback);
RCT_EXTERN_METHOD(toggleFlash);

@end
