package com.example.onlineshop;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.preference.CheckBoxPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreferenceCompat;

public class SettingsFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);

        final SwitchPreferenceCompat onOffNotifications = (SwitchPreferenceCompat) findPreference("notifications");
        final CheckBoxPreference onOffWifi = (CheckBoxPreference) findPreference("wifi enabled");

        Context context = getActivity();
        final SharedPreferences sharedPref = context.getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE
        );

        boolean notifications = sharedPref.getBoolean("notifications",false);
        boolean wifiEnabled = sharedPref.getBoolean("wifi enabled",false);

        onOffNotifications.setChecked(notifications);
        onOffWifi.setChecked(wifiEnabled);

        onOffNotifications.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object o) {
                boolean newValue = !onOffNotifications.isChecked();

                onOffNotifications.setChecked(newValue);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putBoolean(getString(R.string.notifications), newValue);
                editor.apply();

                return false;
            }
        });

        onOffWifi.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object o) {
                boolean newValue = !onOffWifi.isChecked();

                onOffWifi.setChecked(newValue);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putBoolean(getString(R.string.wifi), newValue);
                editor.apply();

                return false;
            }
        });
    }
}
