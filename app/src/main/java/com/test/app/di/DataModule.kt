package com.test.app.di

import com.test.common.network.APIClient
import com.test.common.network.LastFMApiClient
import org.koin.dsl.module

val dataModule = module {

    single<APIClient> { LastFMApiClient() }

}