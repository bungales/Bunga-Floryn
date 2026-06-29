package com.example.bunga_floryn.Note

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.bunga_floryn.data.AppDatabase
import com.example.bunga_floryn.data.entity.NoteEntity
import com.example.bunga_floryn.databinding.ActivityNoteFormBinding
import com.example.bunga_floryn.utils.NotificationHelper
import com.example.bunga_floryn.utils.ReminderHelper
import kotlinx.coroutines.launch

class NoteFormActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNoteFormBinding
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteFormBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Tambah Catatan"
            setDisplayHomeAsUpEnabled(true)
        }
        binding.toolbar.setNavigationOnClickListener { finish() }
        db = AppDatabase.getInstance(this)

        binding.btnSaveNote.setOnClickListener {
            simpanCatatan()
        }
    }

    private fun simpanCatatan() {
        val title = binding.etTitle.text.toString().trim()
        val content = binding.etContent.text.toString().trim()
        val menitStr = binding.etReminderMenit.text.toString().trim()

        if (title.isBlank() || content.isBlank()) {
            Toast.makeText(this, "Judul dan isi catatan wajib diisi!", Toast.LENGTH_SHORT).show()
            return
        }

        val menit = menitStr.toIntOrNull()
        val reminderAt: Long? = if (menit != null && menit > 0) {
            System.currentTimeMillis() + (menit * 60 * 1000L)
        } else null

        lifecycleScope.launch {
            val note = NoteEntity(
                title = title,
                content = content,
                createdAt = System.currentTimeMillis(),
                reminderAt = reminderAt
            )
            db.noteDao().insert(note)

            // Kirim notifikasi langsung saat note disimpan
            val notifIntent = Intent(this@NoteFormActivity, NoteFormActivity::class.java)
            NotificationHelper.showNotification(
                context = this@NoteFormActivity,
                title = "📝 Catatan Tersimpan",
                message = "\"$title\" berhasil disimpan.",
                intent = notifIntent
            )

            // Set reminder kalau user isi menit
            if (menit != null && menit > 0) {
                ReminderHelper.setReminder(
                    context = this@NoteFormActivity,
                    minutesFromNow = menit,
                    title = "⏰ Pengingat Catatan",
                    message = "Jangan lupa: \"$title\"",
                    targetActivity = NoteFormActivity::class.java
                )
                runOnUiThread {
                    Toast.makeText(
                        this@NoteFormActivity,
                        "Catatan disimpan! Pengingat diatur $menit menit lagi.",
                        Toast.LENGTH_LONG
                    ).show()
                }
            } else {
                runOnUiThread {
                    Toast.makeText(this@NoteFormActivity, "Catatan disimpan!", Toast.LENGTH_SHORT).show()
                }
            }

            finish()
        }
    }
}
