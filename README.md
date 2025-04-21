# ☕️ KohiSop - Coffee & Snack Ordering App

Selamat datang di **KohiSop**, aplikasi **CLI** (Command Line Interface) sederhana untuk memesan makanan dan minuman di kafe fiktif! 🎉

---

## ✨ Fitur Utama

- **Pilih Menu**: Tampilkan daftar makanan dan minuman dengan harga dan kode unik.
- **Proses Pesanan**: Input kode menu, jumlah (0 = default 1), dan batalkan (CC).
- **Hitung Pajak**: Otomatis hitung pajak sesuai kategori (makanan/minuman).
- **Konfirmasi & Kuitansi**: Ringkas pesanan, total sebelum/pajak, metode pembayaran, konversi mata uang, diskon, biaya admin.
- **Multi-Method Payment**: Tunai, QRIS (diskon 5%), eMoney.
- **Multi-Currency**: Konversi ke USD, JPY, MYR, EUR.
- **UI Bersih**: Clear screen, delay, dan formatting tabel.

---

## 🚀 Instalasi dan Cara Menjalankan

1. **Clone Repo**
   ```bash
   git clone https://github.com/Vincent14026/KohiSop.git
   cd KohiSop
   ```

2. **Compile**
   ```bash
   javac KohiSop.java
   ```

3. **Jalankan**
   ```bash
   java KohiSop
   ```

> Pastikan file `.java` menggunakan encoding UTF-8 agar simbol mata uang tampil benar.

---

## 🎯 Struktur File

```
├── InputPesanan.java    # Logika input pemesanan, validasi kode & jumlah
├── KohiSop.java         # Entry point, alur program utama
├── KonfirmasiPesanan.java  # Tampilkan ringkasan & hitung pajak
├── Kuitansi.java        # Cetak kuitansi lengkap
├── Menu.java            # Abstract class untuk menu
├── Makanan.java         # Data & tampilan menu makanan
├── Minuman.java         # Data & tampilan menu minuman
├── MataUang.java        # Konversi & simbol mata uang
├── IPayment.java        # Interface pembayaran
├── Tunai.java           # Implementasi pembayaran tunai
├── QRIS.java            # Implementasi QRIS (diskon 5%)
├── eMoney.java          # (Jika ada) eMoney implementation
└── S.java               # Helper: clear screen, move cursor, delay
```

---

## 📝 Contoh Penggunaan

1. **Pilih Makanan**
   ```
   Masukkan kode makanan (atau 'CC' untuk membatalkan)
   Jika sudah selesai, masukan 'Selesai': M1
   Masukkan jumlah (0 untuk default 1, 'S' untuk skip): 2
   ```

2. **Pilih Minuman**
   ```
   Masukkan kode minuman (atau 'CC' untuk membatalkan)
   Jika sudah selesai, masukan 'Selesai': A1
   Masukkan jumlah (0 untuk default 1, 'S' untuk skip): 0
   ```

3. **Konfirmasi & Pembayaran**
   ```
   Konfirmasi Pesanan:
   ─────────────────────────────
   Total Sebelum Pajak : 159000.00 IDR
   Total Pajak         : 12720.00 IDR
   Total Setelah Pajak : 171720.00 IDR

   Pilih metode pembayaran:
   1. Tunai
   2. QRIS
   3. eMoney
   Pilihan Anda: 2

   Total yang harus dibayar : 163133.40 IDR
   Pilih mata uang (USD, JPY, MYR, EUR): USD
   Total dalam USD: $10.88

   ================= KUITANSI PEMBAYARAN =================
   ----------------- Terima kasih dan silakan datang kembali! -----------------
   ```

---
> Created with ☕ and ❤️ oleh Tim Pengembang KohiSop

