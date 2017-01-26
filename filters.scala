/**
  * Created by Mitin on 11/24/2016.
  */
import javax.inject.Inject

import play.api.http.DefaultHttpFilters
import play.filters.cors.CORSFilter

class Filters @Inject() (corsFilter: CORSFilter)
  extends DefaultHttpFilters(corsFilter)
