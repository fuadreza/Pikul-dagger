package io.github.fuadreza.pikul_dagger.ui.main.univ

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import io.github.fuadreza.pikul_dagger.R
import io.github.fuadreza.pikul_dagger.repository.model.Universitas
import io.github.fuadreza.pikul_dagger.ui.main.HomeActivity
import kotlinx.android.synthetic.main.fragment_univ.*
import javax.inject.Inject

/**
 * Dibuat dengan kerjakerasbagaiquda oleh Shifu pada tanggal 28/06/2020.
 *
 */

class UnivFragment : Fragment() {

    @Inject
    lateinit var univViewModel: UnivViewModel

    lateinit var listUniv: List<Universitas>

    private val univAdapter = GroupAdapter<ViewHolder>()

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (activity as HomeActivity).homeComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_univ, container, false)

        if (savedInstanceState == null) univViewModel.getAllUniversitas(context)

        observerUnivState()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
    }

    private fun setupViews() {
        rv_universitas.apply {
            layoutManager = LinearLayoutManager(activity?.application)
            adapter = univAdapter
        }
    }

    private fun observerUnivState() {
        univViewModel.univState.observe(this, Observer { state ->
            when (state) {
                is UnivViewState.OnLoadUnivState -> onLoadUniv(state.univList)
            }
        })
    }



    private fun onLoadUniv(univList: List<Universitas>) {
        univAdapter.clear()
        univList.forEach {
            univAdapter.add(UnivAdapter(it))
        }
        univAdapter.notifyDataSetChanged()
    }

}


