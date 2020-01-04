package com.makeinfo.imeichanger;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.widget.Toast;

import java.util.List;


/**
 * A {@link PreferenceActivity} that presents a set of application settings. On
 * handset devices, settings are presented as a single list. On tablets,
 * settings are split by category, with category headers shown to the left of
 * the list of settings.
 * <p/>
 * See <a href="http://developer.android.com/design/patterns/settings.html">
 * Android Design: Settings</a> for design guidelines and the <a
 * href="http://developer.android.com/guide/topics/ui/settings.html">Settings
 * API Guide</a> for more information on developing a Settings UI.
 */
public class SettingsActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new Preferences()).commit();
    }

    public static class Preferences extends PreferenceFragment {
        @Override
        public void onCreate(final Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            this.getPreferenceManager().setSharedPreferencesName("pref");
            this.getPreferenceManager().setSharedPreferencesMode(
                    Context.MODE_WORLD_READABLE);
            addPreferencesFromResource(R.xml.prefs);

            final Context c = this.getActivity();

           /* this.findPreference("showIcon").setOnPreferenceChangeListener(
                    new Preference.OnPreferenceChangeListener() {

                        @Override
                        public boolean onPreferenceChange(Preference preference, Object newVal) {
                            boolean b = (Boolean) newVal;

                            PackageManager pm = c.getPackageManager();
                            pm.setComponentEnabledSetting(
                                    new ComponentName(c, "com.makeinfo.imeichanger.show_ic"), b ? PackageManager.COMPONENT_ENABLED_STATE_ENABLED
                                            : PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                                    PackageManager.DONT_KILL_APP);

                            return true;
                        }

                    });

            this.findPreference("random").setOnPreferenceChangeListener(
                    new Preference.OnPreferenceChangeListener() {

                        @Override
                        public boolean onPreferenceChange(Preference preference, Object newVal) {
                            boolean b = (Boolean) newVal;
                            return true;
                        }



                    });*/
            this.findPreference("showIcon").setOnPreferenceClickListener(
                    new Preference.OnPreferenceClickListener() {

                        @Override
                        public boolean onPreferenceClick(Preference preference) {
                            Toast.makeText(c, "Buy Pro Version", Toast.LENGTH_SHORT).show();
                            return false;
                        }

                    });
            this.findPreference("random").setOnPreferenceClickListener(
                    new Preference.OnPreferenceClickListener() {

                        @Override
                        public boolean onPreferenceClick(Preference preference) {
                            Toast.makeText(c, "Buy Pro Version", Toast.LENGTH_SHORT).show();
                            return false;
                        }

                    });
            this.findPreference("buy").setOnPreferenceClickListener(
                    new Preference.OnPreferenceClickListener() {

                        @Override
                        public boolean onPreferenceClick(Preference preference) {
                            Uri uri = Uri.parse("market://details?id=com.makeinfo.imeieditorpro" );
                            Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                            try {
                                startActivity(goToMarket);
                            } catch (ActivityNotFoundException e) {
                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=com.makeinfo.imeieditorpro")));
                            }
                            return false;
                        }

                    });
        }
    }
}
