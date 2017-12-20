package br.edu.ifma.dai.maurolcsilva.projetosemaforo;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class JanelaInicial extends AppCompatActivity implements LocationListener {

    private TextView lblLatitude;
    private TextView lblLongitude;
    private double latitude;
    private double longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_janela_inicial);
        //Recupera os componentes do XML e transforma em componentes Java
        lblLatitude = findViewById(R.id.lblLatitudeResultado);
        lblLongitude = findViewById(R.id.lblLongitudeResultado);
        //Pegamos a partir do Sistema, um gerenciador de localização padrão
        //do Android.
        //Assim como os sensores, que podem ter várias implementações de software
        //que gerenciam o hardware, podemos ter vários softwares que gerenciam o serviço
        //de localização.
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        //Código disponibilizado pelo Android para verificar as permissões
        //para uso dos recursos de localização
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        //O método requestLocationUpdate irá trabalhar em conjunto com
        //o método onLocationChanged. O primeiro configura o modelo de captura dos dados
        //de posicionamento e o segundo é chamado a cada mudança de posicionamento (por tempo
        //ou por distância)
        lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 2000, 0, this);
    }

    @Override
    public void onLocationChanged(Location location) {

        //Este método é chamado a cada vaiação de tempo ou distância
        this.latitude = location.getLatitude();
        this.longitude = location.getLongitude();
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {
        //Este método é chamado cada vez que muda a acurácia
        //dos dados obtidos pelo Provedor
    }

    @Override
    public void onProviderEnabled(String s) {
        //Método chamado quando após um queda do Provedor
        //ele volta a funcionar
    }

    @Override
    public void onProviderDisabled(String s) {
        //Método chamado quando o Provedor cai
    }

    public void buscarLocalizacao(View v){
        lblLatitude.setText(String.valueOf(this.latitude));
        lblLongitude.setText(String.valueOf(this.longitude));
    }

    public void mostrarMapa(View v){
        double lat,lng;
        //Recuperação da latitude e longitude obtidas a partir
        //do dados expostos no TextView
        lat = Double.parseDouble(lblLatitude.getText().toString());
        lng = Double.parseDouble(lblLongitude.getText().toString());

        //Criação da intent que irá chamar a Activity do Mapa
        Intent it = new Intent(this,JanelaMapa.class);
        //Criação do Bundle para receber a latitude e longitude obtidas
        //e encaminhá-las a Activity do Mapa
        Bundle parametro = new Bundle();
        parametro.putDouble("latitude",lat);
        parametro.putDouble("longitude",lng);
        //Associa o Bundle a Intent
        it.putExtras(parametro);
        //Inicia a Activity do Mapa
        startActivity(it);
    }
}
