package br.edu.ifma.dai.maurolcsilva.projetosemaforo;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class JanelaMapa extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private double latitude;
    private double longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_janela_mapa);
        //recuperamos a intent enviada
        Intent itrecebida = getIntent();
        //recuperamos o parametro enviado
        Bundle parametrorecebido = itrecebida.getExtras();
        //Atribuimos os valores ao atributos de latitude e longitude
        this.latitude = parametrorecebido.getDouble("latitude");
        this.longitude = parametrorecebido.getDouble("longitude");
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        //Definir o tipo de mapa
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        //Criação de um objeto LatLng a partir das coordenadas recebida
        LatLng posicao = new LatLng(latitude,longitude);
        //Criação do Marcador
        MarkerOptions marcador = new MarkerOptions();
        marcador.position(posicao);
        marcador.title("Minha Posicao");
        marcador.snippet("Este é um comentário");
        mMap.addMarker(marcador);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(posicao,15));

        // Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(-34, 151);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));

    }
}
