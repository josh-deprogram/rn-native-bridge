/**
 * Swift > JS Bridged component
 */

import React, { Component } from 'react';
import ReactNative, {
  AppRegistry,
  StyleSheet,
  Text,
  View,
  NativeModules,
  TouchableOpacity
} from 'react-native';

// Reference the Bridged Module.
// Log out avalible properties/methods.
const NativeBridge = NativeModules.SwiftBridge
console.log(NativeBridge);

class SwiftBridge extends Component {
  
  componentDidMount(){
    // Listen to the Event dispatched from the Native environment.
    const subscription = ReactNative.NativeAppEventEmitter.addListener(
        'EventReminder',
        (reminder) => {
            console.log('EVENT', reminder)
            console.log('name: ' + reminder.name)
            console.log('location: ' + reminder.location)
            console.log('date: ' + reminder.date)
        }
    );
  }

  onClick(){
    // Call the sample method, passing across some arguments
    NativeBridge.addEvent("_One", "_Two", 3, function(o) {
        console.log('In Callback');
        console.log(o);
    });

    // Toggle the Flash light...
    NativeBridge.toggleFlash()

    console.log('clicked.. call native_ ', NativeBridge);
  }

  render() {
    return (
      <View style={styles.container}>
        <TouchableOpacity style={{flex: 1, alignItems: 'center', justifyContent: 'center' }} onPress={this.onClick.bind(this)}>
          <Text style={styles.welcome}>
            Swift to React Native Bridge!
          </Text>
          <Text style={styles.instructions}>
            Press to flash
          </Text>
        </TouchableOpacity>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#F5FCFF',
  },
  welcome: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10,
  },
  instructions: {
    textAlign: 'center',
    color: '#333333',
    marginBottom: 5,
  },
});

AppRegistry.registerComponent('SwiftBridge', () => SwiftBridge);
