package com.makeinfo.imeichanger;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * Created by Basil on 9/1/2015.
 */
public class xposed implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
       // XposedBridge.log("Testing Template :) .....");
        if (android.os.Build.VERSION.SDK_INT < 22){
            XposedHelpers.findAndHookMethod("com.android.internal.telephony.PhoneSubInfo", loadPackageParam.classLoader, "getDeviceId",
                    new XC_MethodHook() {
                        protected void afterHookedMethod(MethodHookParam methodhookparam)
                                throws Throwable {
                            super.afterHookedMethod(methodhookparam);
                            XSharedPreferences xsharedpreferences = new XSharedPreferences("com.makeinfo.imeichanger", "IMEI_settings");
                            String phoneinfo=xsharedpreferences.getString("CurrentIMEI", xsharedpreferences.getString("OriginalIMEI", ""));

                            methodhookparam.setResult(phoneinfo);

                        }

                    });
            XposedHelpers.findAndHookMethod("com.android.internal.telephony.gsm.GSMPhone", loadPackageParam.classLoader, "getDeviceId", new Object[] {
                    new XC_MethodHook() {



                        protected void afterHookedMethod(de.robv.android.xposed.XC_MethodHook.MethodHookParam methodhookparam)
                                throws Throwable
                        {
                            super.afterHookedMethod(methodhookparam);
                            XSharedPreferences xsharedpreferences = new XSharedPreferences("com.makeinfo.imeichanger", "IMEI_settings");
                            String gsmphone=xsharedpreferences.getString("CurrentIMEI", xsharedpreferences.getString("OriginalIMEI", ""));
                            methodhookparam.setResult(xsharedpreferences.getString("CurrentIMEI", xsharedpreferences.getString("OriginalIMEI", "")));
                            XposedBridge.log(gsmphone);
                        }


                    }
            });
            XposedHelpers.findAndHookMethod("android.telephony.TelephonyManager", loadPackageParam.classLoader, "getDeviceId", new Object[] {
                    new XC_MethodHook() {



                        protected void afterHookedMethod(de.robv.android.xposed.XC_MethodHook.MethodHookParam methodhookparam)
                                throws Throwable
                        {
                            super.afterHookedMethod(methodhookparam);
                            XSharedPreferences xsharedpreferences = new XSharedPreferences("com.makeinfo.imeichanger", "IMEI_settings");
                            String tm = xsharedpreferences.getString("CurrentIMEI", xsharedpreferences.getString("OriginalIMEI", ""));
                            methodhookparam.setResult(xsharedpreferences.getString("CurrentIMEI", xsharedpreferences.getString("OriginalIMEI", "")));
                            XposedBridge.log(tm);
                        }


                    }
            });
        }
        if (android.os.Build.VERSION.SDK_INT >= 22)
        {
            XposedHelpers.findAndHookMethod("com.android.internal.telephony.PhoneSubInfo", loadPackageParam.classLoader, "getDeviceId", new Object[] {
                    new XC_MethodHook() {

                                           protected void afterHookedMethod(de.robv.android.xposed.XC_MethodHook.MethodHookParam methodhookparam)
                                throws Throwable
                        {
                            super.afterHookedMethod(methodhookparam);
                            XSharedPreferences xsharedpreferences = new XSharedPreferences("com.makeinfo.imeichanger", "IMEI_settings");
                            methodhookparam.setResult(xsharedpreferences.getString("CurrentIMEI", xsharedpreferences.getString("OriginalIMEI", "")));
                        }


                    }
            });
            XposedHelpers.findAndHookMethod("com.android.internal.telephony.PhoneProxy", loadPackageParam.classLoader, "getDeviceId", new Object[] {
                    new XC_MethodHook() {



                        protected void afterHookedMethod(de.robv.android.xposed.XC_MethodHook.MethodHookParam methodhookparam)
                                throws Throwable
                        {
                            super.afterHookedMethod(methodhookparam);
                            XSharedPreferences xsharedpreferences = new XSharedPreferences("com.makeinfo.imeichanger", "IMEI_settings");
                            methodhookparam.setResult(xsharedpreferences.getString("CurrentIMEI", xsharedpreferences.getString("OriginalIMEI", "")));
                        }


                    }
            });
            XposedHelpers.findAndHookMethod("android.telephony.TelephonyManager", loadPackageParam.classLoader, "getDeviceId", new Object[] {
                    new XC_MethodHook() {



                        protected void afterHookedMethod(de.robv.android.xposed.XC_MethodHook.MethodHookParam methodhookparam)
                                throws Throwable
                        {
                            super.afterHookedMethod(methodhookparam);
                            XSharedPreferences xsharedpreferences = new XSharedPreferences("com.makeinfo.imeichanger", "IMEI_settings");
                            methodhookparam.setResult(xsharedpreferences.getString("CurrentIMEI", xsharedpreferences.getString("OriginalIMEI", "")));
                        }



                    }
            });
        }
    }
}