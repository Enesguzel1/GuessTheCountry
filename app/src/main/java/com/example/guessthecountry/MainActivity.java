package com.example.guessthecountry;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.guessthecountry.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class MainActivity extends AppCompatActivity {
    String cevap;
    int score = 0;
    Handler handler;
    Runnable runnable;
    int[] photoOfCountries;
    HashMap<Integer, String> hashMap;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        hashMap = new HashMap<>();
        hashMap.put(R.drawable.almanya, "almanya");
        hashMap.put(R.drawable.amerika, "amerika");
        hashMap.put(R.drawable.avustralya, "avustralya");
        hashMap.put(R.drawable.avusturya, "avusturya");
        hashMap.put(R.drawable.azerbaycan, "azerbaycan");
        hashMap.put(R.drawable.belcika, "belçika");
        hashMap.put(R.drawable.bosna_hersek, "bosna hersek");
        hashMap.put(R.drawable.brazilya, "brezilya");
        hashMap.put(R.drawable.cek_cumhuriyet, "çek cumhuriyeti");
        hashMap.put(R.drawable.cin, "çin");
        hashMap.put(R.drawable.endonezya, "endonezya");
        hashMap.put(R.drawable.ermenistan, "ermenistan");
        hashMap.put(R.drawable.filistin, "filistin");
        hashMap.put(R.drawable.gurcistan, "gürcistan");
        hashMap.put(R.drawable.hindistan, "hindistan");
        hashMap.put(R.drawable.hirvatistan, "hırvatistan");
        hashMap.put(R.drawable.ispanya, "ispanya");
        hashMap.put(R.drawable.israil, "israil");
        hashMap.put(R.drawable.isvec, "isveç");
        hashMap.put(R.drawable.isvicre, "isviçre");
        hashMap.put(R.drawable.italya, "italya");
        hashMap.put(R.drawable.japonya, "japonya");
        hashMap.put(R.drawable.katar, "katar");
        hashMap.put(R.drawable.kazakistan, "kazakistan");
        hashMap.put(R.drawable.kibris, "kıbrıs");
        hashMap.put(R.drawable.kuba, "küba");
        hashMap.put(R.drawable.macaristan, "macaristan");
        hashMap.put(R.drawable.peru, "peru");
        hashMap.put(R.drawable.polonya, "polonya");
        hashMap.put(R.drawable.portekiz, "portekiz");
        hashMap.put(R.drawable.romanya, "romanya");
        hashMap.put(R.drawable.rusya, "rusya");
        hashMap.put(R.drawable.sirbistan, "sırbistan");
        hashMap.put(R.drawable.sri_lanka, "sri lanka");
        hashMap.put(R.drawable.tacikistan, "tacikistan");
        hashMap.put(R.drawable.ukrayna, "ukrayna");
        hashMap.put(R.drawable.turkey, "türkiye");

        photoOfCountries = new int[]{R.drawable.almanya, R.drawable.amerika, R.drawable.avustralya, R.drawable.avusturya,
                R.drawable.azerbaycan, R.drawable.belcika, R.drawable.bosna_hersek, R.drawable.brazilya, R.drawable.cek_cumhuriyet,
                R.drawable.cin, R.drawable.endonezya, R.drawable.ermenistan, R.drawable.filistin, R.drawable.gurcistan,
                R.drawable.hindistan, R.drawable.hirvatistan, R.drawable.ispanya, R.drawable.israil, R.drawable.isvec,
                R.drawable.isvicre, R.drawable.italya, R.drawable.japonya, R.drawable.katar, R.drawable.kazakistan,
                R.drawable.kibris, R.drawable.kuba, R.drawable.macaristan, R.drawable.peru, R.drawable.polonya,
                R.drawable.portekiz, R.drawable.romanya, R.drawable.rusya, R.drawable.sirbistan, R.drawable.sri_lanka,
                R.drawable.tacikistan, R.drawable.ukrayna, R.drawable.turkey};

        binding.startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.rulesTextView.setVisibility(View.INVISIBLE);
                binding.countryImageView.setVisibility(View.VISIBLE);
                binding.tahminEditText.setVisibility(View.VISIBLE);
                binding.checkTheGuessButton.setVisibility(View.VISIBLE);
                binding.kalanZamanText.setVisibility(View.VISIBLE);
                showTheCountry();
                binding.startButton.setVisibility(View.INVISIBLE);
                new CountDownTimer(60000, 1000) {
                    @Override
                    public void onTick(long l) {
                        binding.kalanZamanText.setText("Kalan Zamanınız: " + l / 1000);
                    }
                    @Override
                    public void onFinish() {
                        binding.kalanZamanText.setVisibility(View.INVISIBLE);
                        binding.startButton.setVisibility(View.VISIBLE);
                        binding.countryImageView.setVisibility(View.INVISIBLE);
                        binding.tahminEditText.setVisibility(View.INVISIBLE);
                        binding.checkTheGuessButton.setVisibility(View.INVISIBLE);
                        Toast.makeText(MainActivity.this, "Zaman doldu skorunuz: " + score, Toast.LENGTH_LONG).show();
                        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                        alert.setTitle("Oyun Bitti");
                        alert.setMessage("Tekrar oynamak ister misiniz?");
                        alert.setPositiveButton("Evet!", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = getIntent();
                                finish();
                                startActivity(intent);
                            }
                        });
                        alert.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(getApplicationContext(), "Oyun Bitti!", Toast.LENGTH_LONG).show();
                            }
                        });
                        alert.show();
                    }
                }.start();
            }
        });

    }
    public void showTheCountry() {
        handler = new Handler();

        runnable = new Runnable() {
            @Override
            public void run() {
                Random random = new Random();
                int rndNmbr = random.nextInt(39);
                binding.countryImageView.setImageResource(photoOfCountries[rndNmbr]);
                cevap = hashMap.get(photoOfCountries[rndNmbr]);
                System.out.println(cevap);
                binding.checkTheGuessButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (binding.tahminEditText.getText().toString().equals(cevap)) {
                            Toast.makeText(getApplicationContext(), "İyi Gidiyorsun", Toast.LENGTH_SHORT).show();
                            binding.tahminEditText.getText().clear();
                            score++;
                        }
                    }
                });
                handler.postDelayed(this, 10000);
            }
        };
        handler.post(runnable);
    }

}