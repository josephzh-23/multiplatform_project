package com.example.multi_platform_android_project.Pagination


//@OptIn(ExperimentalPagingApi::class)
//class BeerRemoteMediator(
//    private val beerDb: BeerDatabase,
//    private val beerApi: BeerApi
//): RemoteMediator<Int, BeerEntity>() {
//
//    override suspend fun load(
//        loadType: LoadType,
//        state: PagingState<Int, BeerEntity>
//    ): MediatorResult {
//        return try {
//            val loadKey = when(loadType) {
//                LoadType.REFRESH -> 1
//                LoadType.PREPEND -> return MediatorResult.Success(
//                    endOfPaginationReached = true
//                )
//                LoadType.APPEND -> {
//                    val lastItem = state.lastItemOrNull()
//                    if(lastItem == null) {
//                        1
//                    } else {
//                        (lastItem.id / state.config.pageSize) + 1
//                    }
//                }
//            }
//
//            val beers = beerApi.getBeers(
//                page = loadKey,
//                pageCount = state.config.pageSize
//            )
//
//            beerDb.withTransaction {
//                if(loadType == LoadType.REFRESH) {
//                    beerDb.dao.clearAll()
//                }
//                val beerEntities = beers.map { it.toBeerEntity() }
//                beerDb.dao.upsertAll(beerEntities)
//            }
//
//            MediatorResult.Success(
//                endOfPaginationReached = beers.isEmpty()
//            )
//        } catch(e: IOException) {
//            MediatorResult.Error(e)
//        } catch(e: HttpException) {
//            MediatorResult.Error(e)
//        }
//    }
//}