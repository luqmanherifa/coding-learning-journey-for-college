/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author confus1on
 */
public class Node {
    String nama_tugas;
    String keterangan;
    String metode;
    Node next;
    Node prev;
    
    Node(String new_namaTugas, String new_keterangan, String new_Metode)
    {
        nama_tugas = new_namaTugas;
        keterangan = new_keterangan;
        metode = new_Metode;
        next = null;
    }
}
