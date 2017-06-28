/**
 *  Android Bridge App
 */

import React, { Component } from 'react';
import {
  AppRegistry,
  StyleSheet,
  Text,
  View
} from 'react-native';

class AndroidBridge extends Component {
  render() {
    return (
      <View style={styles.container}>
        <Text style={styles.welcome}>
          Not much happening in here!. Check the Android APP!
        </Text>
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
  }
});

AppRegistry.registerComponent('AndroidBridge', () => AndroidBridge);
