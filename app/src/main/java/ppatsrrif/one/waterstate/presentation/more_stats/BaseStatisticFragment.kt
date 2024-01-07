package ppatsrrif.one.waterstate.presentation.more_stats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ppatsrrif.one.waterstate.R
import ppatsrrif.one.waterstate.databinding.FragmentBaseStatisticBinding

class BaseStatisticFragment : Fragment() {

    private lateinit var binding: FragmentBaseStatisticBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentBaseStatisticBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}