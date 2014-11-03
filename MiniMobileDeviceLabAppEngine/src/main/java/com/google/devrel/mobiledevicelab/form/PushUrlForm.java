/**
 * Copyright 2014 Google Inc. All Rights Reserved.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 **/
package com.google.devrel.mobiledevicelab.form;

import java.util.List;


/**
 * Pojo representing a url to push on the client side.
 */
public class PushUrlForm {

  /**
   * The token provided by Google OAuth service.
   */
  private String token;

  /**
   * The id of the user linked to the access token
   */
  private String userId;

  /**
   * The url to push to Android devices
   */
  private String url;

  /**
   * Whether to push to the same browser on all devices. If true, it means that
   * the url is pushed to all devices, using the browserPackageName
   */
  private boolean sameBrowserForAllDevices;

  /**
   * The package name of the browser to push, if sameBrowserForAllDevices is
   * true
   */
  private String browserPackageName;

  /**
   * The array of devices to push to, and which browser to use for each, if
   * sameBrowserForAllDevices is false
   */
  private List<DeviceBrowserForm> individualDevices;

  /**
   * Just making the default constructor private.
   */
  private PushUrlForm() {
  }

  public String getToken() {
    return token;
  }

  public String getUserId() {
    return userId;
  }

  public String getUrl() {
    if (!url.contains("http://") && !url.contains("https://")) {
      url = "http://" + url;
    }
    return url;
  }

  public boolean isSameBrowserForAllDevices() {
    return sameBrowserForAllDevices;
  }

  public String getBrowserPackageName() {
    return browserPackageName;
  }

  public List<DeviceBrowserForm> getIndividualDevices() {
    return individualDevices;
  }

  /**
   * @param id
   * @return null if no browser package name provided for the device and
   *         sameBrowserForAllDevices = false
   */
  public String getBrowserPackageNameForDeviceId(long id) {
    if (sameBrowserForAllDevices) {
      return browserPackageName;
    } else {
      for (DeviceBrowserForm device : individualDevices) {
        if (device.getDeviceId() == id && device.getBrowserPackageName() != null) {
          return device.getBrowserPackageName();
        }
      }
    }
    return null;
  }

  /**
   * @param id
   * @return true if the device with the given id is in the list of individual
   *         devices
   */
  public boolean isDeviceInList(long id) {
    for (DeviceBrowserForm device : individualDevices) {
      if (device.getDeviceId() == id) {
        return true;
      }
    }
    return false;
  }


  /**
   * This is used to specific a specific browser for a specific device
   * 
   * @author nmasse
   */
  public static class DeviceBrowserForm {

    /**
     * The id of the device
     */
    private long deviceId;

    /**
     * The package name of the browser
     */
    private String browserPackageName;

    /**
     * Just making the default constructor private.
     */
    private DeviceBrowserForm() {
    };

    public DeviceBrowserForm(long deviceId, String browserPackageName) {
      this.deviceId = deviceId;
      this.browserPackageName = browserPackageName;
    }

    public long getDeviceId() {
      return deviceId;
    }

    public String getBrowserPackageName() {
      return browserPackageName;
    }

  }

}
