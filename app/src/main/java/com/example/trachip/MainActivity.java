package com.example.trachip;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.splashscreen.SplashScreen;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;


import com.example.trachip.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private IpViewModle viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SplashScreen.installSplashScreen(this);


        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = new ViewModelProvider(this).get(IpViewModle.class);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;



        });

        binding.etIpAddress.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {}
            @Override public void afterTextChanged(Editable s) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                binding.resultCard.setVisibility(View.GONE);
                binding.tvStatus.setVisibility(View.GONE);
            }
        });

        binding.btnFind.setOnClickListener(v -> {
            String ip = binding.etIpAddress.getText().toString().trim();

            if (ip.isEmpty()) {
                binding.etIpAddress.setError("Please enter an IP address");
                return;
            }

            viewModel.lookupIp(ip);
        });

        observeViewModel();

    }
    private void observeViewModel() {

        // Loading state — show progressbar, disable button
        viewModel.getIsLoading().observe(this, isLoading -> {
            if (isLoading) {
                binding.progressBar.setVisibility(View.VISIBLE);
                binding.btnFind.setEnabled(false);
                binding.resultCard.setVisibility(View.GONE);
                binding.tvStatus.setVisibility(View.GONE);
            } else {
                binding.progressBar.setVisibility(View.GONE);
                binding.btnFind.setEnabled(true);
            }
        });

        // Success state — populate and show result card
        viewModel.getIpResult().observe(this, data -> {
            binding.tvStatus.setVisibility(View.VISIBLE);
            binding.resultCard.setVisibility(View.VISIBLE);

            binding.tvIp.setText(data.getIp());
            binding.tvCountry.setText("🌍  Country: " + data.getCountry());
            binding.tvCity.setText("🏙  City:      " + data.getCity());
            binding.tvLatLon.setText("Lat/Long: " + data.getLat() + "°N, " + data.getLon() + "°E");
        });

        // Error state — show toast, hide result card
        viewModel.getErrorMessage().observe(this, error -> {
            binding.tvStatus.setVisibility(View.GONE);
            binding.resultCard.setVisibility(View.GONE);
            Toast.makeText(this, error, Toast.LENGTH_LONG).show();
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Null out binding to avoid memory leaks
        binding = null;
    }
}
