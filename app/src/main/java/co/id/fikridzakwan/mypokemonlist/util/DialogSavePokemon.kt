package co.id.fikridzakwan.mypokemonlist.util

import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import co.id.fikridzakwan.mypokemonlist.databinding.LayoutDialogSavePokemonBinding

class DialogSavePokemon : DialogFragment() {

    private var _binding: LayoutDialogSavePokemonBinding? = null
    private val binding get() = _binding!!
    private lateinit var onSaveClick: (nickname: String) -> Unit

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = LayoutDialogSavePokemonBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCancel.setOnClickListener {
            dismiss()
        }

        binding.btnSave.setOnClickListener {
            onSaveClick.invoke(binding.edtNickname.text.toString())
            Toast.makeText(requireContext(), "Menambahkan pokemon ke tas", Toast.LENGTH_SHORT).show()
            dismiss()
        }
    }

    override fun onResume() {
        super.onResume()

        val window = dialog!!.window ?: return
        val params = window.attributes
        params.width = requireContext().resources.displayMetrics.widthPixels - 40.toPx()
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT
        window.attributes = params
    }

    private fun Int.toPx(): Int {
        return (this * Resources.getSystem().displayMetrics.density).toInt()
    }

    companion object {
        @JvmStatic
        fun newInstance(onSaveClick: (nickname: String) -> Unit) = DialogSavePokemon().apply {
            this.onSaveClick = onSaveClick
        }
    }
}