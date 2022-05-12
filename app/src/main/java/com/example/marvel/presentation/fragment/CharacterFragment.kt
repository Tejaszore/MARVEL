//package com.example.marvel.presentation.fragment
//
//import android.os.Bundle
//import android.util.Log
//import android.view.*
//import androidx.fragment.app.Fragment
//import androidx.lifecycle.Observer
//import androidx.lifecycle.ViewModelProvider
//import androidx.lifecycle.ViewModelProviders
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.example.marvel.R
//import com.example.marvel.presentation.adapter.RecyclerViewAdapter
//import com.example.marvel.presentation.viewmodel.CharacterListViewModel
//import com.example.marvel.presentation.viewmodel.CharacterViewModel
//import com.initishbhatt.marvelsuperheros.api.model.AllCharactersModel
//import kotlinx.android.synthetic.main.fragment_charaterslist.*
//
//class CharacterFragment : Fragment() {
//
//    private lateinit var recyclerViewAdapter: RecyclerViewAdapter
//    private lateinit var mainActivityViewModel: CharacterViewModel
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//    }
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
////        return super.onCreateView(inflater, container, savedInstanceState)
//        return inflater.inflate(R.layout.fragment_charaterslist, container, false)
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        initRecyclerView()
//        initViewModel()
//    }
//
//    private fun initRecyclerView() {
//        character_recycler_view.layoutManager = LinearLayoutManager(this.context)
//        recyclerViewAdapter = RecyclerViewAdapter()
//        character_recycler_view.adapter = recyclerViewAdapter
//    }
//
//    private fun initViewModel() {
//        mainActivityViewModel = ViewModelProviders.of(this).get(CharacterViewModel::class.java)
//        mainActivityViewModel.getLiveDataObserver().observe(this, object : Observer<List<AllCharactersModel>> {
//            override fun onChanged(t: AllCharactersModel?) {
//                if(t != null) {
//                    recyclerViewAdapter.setUpdatedData(t.items)
//                    recyclerViewAdapter.notifyDataSetChanged()
//                } else {
////                    Toast.makeText(this, "error in getting the date", Toast.LENGTH_SHORT).show()
//                    Log.e("Error in loading data","error in getting the date")
//
//                }
//            }
//        })
//        mainActivityViewModel.makeApicall()
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//    }
//
////    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
////        super.onCreateOptionsMenu(menu, inflater)
////        inflater!!.inflate(R.menu.character_list, menu)
////    }
////
////    override fun onOptionsItemSelected(item: MenuItem): Boolean {
////        when (item!!.itemId) {
////            R.id.action_reload -> {
////                // Sends the click event through the onReloadClickSubject to the subscribing presenter
////                onReloadClickSubject.onNext(Object())
////                return true
////            }
////        }
////        return super.onOptionsItemSelected(item)
////    }
////
////    override fun getViewModel(): CharacterListViewModel {
////        return viewModel
////    }
////
////    override fun onReloadClick(): Observable<Any> {
////        return onReloadClickSubject
////    }
//}