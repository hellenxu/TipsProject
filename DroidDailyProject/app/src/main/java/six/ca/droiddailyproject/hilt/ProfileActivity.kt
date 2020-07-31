package six.ca.droiddailyproject.hilt

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import six.ca.droiddailyproject.databinding.ActProfileBinding
import javax.inject.Inject

/**
 * @author hellenxu
 * @date 2020-07-30
 * Copyright 2020 Six. All rights reserved.
 */

@AndroidEntryPoint
class ProfileActivity: AppCompatActivity() {
    @set:Inject
    internal lateinit var userRepo: UserRepo
    private lateinit var binding: ActProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val profile = userRepo.getUserProfile()
        binding.tvName.text = profile.name
        binding.tvId.text = profile.id.toString()
    }
}