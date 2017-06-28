/**
 * Android Bridge - Native Module
 */

import React, { Component } from 'react';
import {
  AppRegistry,
  StyleSheet,
  Text,
  View,
  NativeModules,
  TouchableOpacity
} from 'react-native';

// Define our Native Modules
const ToastModule = NativeModules.ToastModule;
const FlashModule = NativeModules.FlashModule;
const CallModule = NativeModules.CallModule;

class AndroidBridge extends Component {

  constructor (props) {
    super(props);

    this.state = {
      msg: ''
    };
  }

  componentDidMount(){
    // Logs out the avalible Bridged Methods/Constants
    // console.log(ToastModule)
    // console.log(FlashModule)
    console.log(CallModule)
  }

  onClick(){

    // Call out Native Methods...
    ToastModule.show('Ohhh nice your making a call bruv', ToastModule.LONG);
    // FlashModule.turnOnFlash();
    CallModule.call('1234567890');
    this.setState({ msg: 'Calling number...' });
    setTimeout(()=>{
      this.setState({ msg: 'Hows was your phone call then?' })
    }, 2000)
  }

  render() {
    return (
      <View style={styles.container}>
        <TouchableOpacity style={{flex: 1, alignItems: 'center', justifyContent: 'center' }} onPress={this.onClick.bind(this)}>
          <Text style={styles.welcome}>
            Android Bridge - In App Callinbg
          </Text>
          <Text style={styles.instructions}>
            Press Me - PHONE SOMEONE
          </Text>
          <Text style={styles.instructions}>
            {this.state.msg}
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
    margin: 10
  },
  instructions: {
    textAlign: 'center',
    color: 'brown',
    marginBottom: 5,
  },
});

AppRegistry.registerComponent('AndroidBridge', () => AndroidBridge);
