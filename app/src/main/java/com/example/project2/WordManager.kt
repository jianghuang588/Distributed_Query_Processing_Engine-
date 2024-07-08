package com.example.project2

import android.annotation.SuppressLint
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONArray
import org.json.JSONObject
import java.util.concurrent.TimeUnit
class WordManager {
    private val okHttpClient: OkHttpClient

    init {
        val builder = OkHttpClient.Builder()
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        okHttpClient = builder.build()

        builder.connectTimeout(15, TimeUnit.SECONDS)
        builder.readTimeout(15,TimeUnit.SECONDS)
        builder.writeTimeout(15, TimeUnit.SECONDS)
    }

    @SuppressLint("SuspiciousIndentation")
    fun retrieveSynonyms(apiKey: String, synonyms: String): MutableCollection<Words> {
        val request = Request.Builder()
            // https://rapidapi.com/dpventures/api/wordsapi/
            // I reference  api, url(), get(), addHeader(), build() from above website
            .url("https://wordsapiv1.p.rapidapi.com/words/$synonyms/synonyms")
            .get()
            .addHeader("X-RapidAPI-Key", apiKey)
            .build()
        val response = okHttpClient.newCall(request).execute()
        val tweets: MutableCollection<Words> = mutableListOf()
        val responseString: String? = response.body?.string()
        val json: JSONObject = JSONObject(responseString)
        val statuses: JSONArray = json.getJSONArray("synonyms")
        val firstString: String = statuses[0].toString();
        val secondString: String = statuses[1].toString();
        val thirdString: String = statuses[2].toString();
        val fourthString: String = statuses[3].toString();
        val fifthString: String = statuses[4].toString();
        val sixString: String = statuses[5].toString();
        val sevenString: String = statuses[7].toString();
        val eightString: String = statuses[8].toString();
        val nineString: String = statuses[9].toString();
        val tenString: String = statuses[10].toString();
        val eleString: String = statuses[11].toString();
        val twelveString: String = statuses[12].toString();
        val thirString: String = statuses[13].toString();
        val fourString: String = statuses[14].toString();
        val fifString: String = statuses[15].toString();
        val sixtString: String = statuses[16].toString();
        val seventString: String = statuses[17].toString();
        val eighteString: String = statuses[18].toString();
        val nineteenString: String = statuses[19].toString();
        val tweltyString: String = statuses[20].toString();
        val tweltyoneString: String = statuses[21].toString();
        val twoString: String = statuses[22].toString();
        val threeString: String = statuses[23].toString();
        val tweet = Words(
                source =  fifthString,
                definition = firstString,
                examples =  secondString,
                random = thirdString,
                syllable = fourthString,
                view5 = fifthString,
                view6 = sixString,
                view7 = sevenString,
                view8 = eightString,
                view9 = nineString,
                view10 = tenString,
                view11 = eleString,
                view12 = twelveString,
                view13 = thirString,
                view14 = fourString,
                view15 = fifString,
                view16 = sixtString,
                view17 = seventString,
                view18 = eighteString,
                view19 = nineteenString,
                view20 = tweltyString,
                view21 = tweltyoneString,
                view22 = twoString,
                view23 = threeString,
                )
            tweets.add(tweet)
        return tweets
    }
    fun retrieveAntonyms(apiKey: String, antonyms: String): MutableCollection<newWord> {
        val request = Request.Builder()
            // https://rapidapi.com/dpventures/api/wordsapi/
            // I reference  api, url(), get(), addHeader(), build() from above website
            .url("https://wordsapiv1.p.rapidapi.com/words/$antonyms/antonyms")
            .get()
            .addHeader("X-RapidAPI-Key", apiKey)
            .build()
        val response = okHttpClient.newCall(request).execute()
        val tweets: MutableCollection<newWord> = mutableListOf()
        val responseString: String? = response.body?.string()
        val json: JSONObject = JSONObject(responseString)
        val statuses: JSONArray = json.getJSONArray("antonyms")
        val firstString: String = statuses[0].toString();
        val tweet = newWord(
            task = firstString,
            task1 = "",
            task2 =  "",
            task3 = "",
        )
        tweets.add(tweet)
        return tweets
    }
    fun retrieveDefinition(apiKey: String, definition: String): MutableCollection<newWord> {
        val request = Request.Builder()
            // https://rapidapi.com/dpventures/api/wordsapi/
            // I reference  api, url(), get(), addHeader(), build() from above website
            .url("https://wordsapiv1.p.rapidapi.com/words/$definition/definitions")
            .get()
            .addHeader("X-RapidAPI-Key", apiKey)
            .build()
        val response = okHttpClient.newCall(request).execute()
        val tweets: MutableCollection<newWord> = mutableListOf()
        val responseString: String? = response.body?.string()
        val json: JSONObject = JSONObject(responseString)
        val statuses: JSONArray = json.getJSONArray("definitions")
        for (i in 0 until statuses.length()) {
            val curr = statuses.getJSONObject(i)
            val definition = curr.getString("definition")
            val tone = curr.getString("partOfSpeech")
            val tweet = newWord(
                task =  tone,
                task1 = definition,
                task2 =  "",
                task3 = "",
            )
            tweets.add(tweet)
        }
        return tweets
    }

    fun retrieveExampleSentence(apiKey: String, word: String): MutableCollection<Words> {

        val request = Request.Builder()
            // https://rapidapi.com/dpventures/api/wordsapi/
            // I reference  api, url(), get(), addHeader(), build() from above website
            .url("https://wordsapiv1.p.rapidapi.com/words/$word/examples")
            .get()
            .addHeader("X-RapidAPI-Key", apiKey)
            .build()
        val response = okHttpClient.newCall(request).execute()
        val tweets: MutableCollection<Words> = mutableListOf()
        val responseString: String? = response.body?.string()
        val json: JSONObject = JSONObject(responseString)
        val currWordType = json.getJSONArray("examples")
        val firstString = currWordType[0].toString()
        val secondString = currWordType[1].toString()
        val thirdString = currWordType[2].toString()
        val string4 = currWordType[3].toString()
        val string5 = currWordType[4].toString()
        val string6 = currWordType[5].toString()
        val string7 = currWordType[6].toString()
        val string8 = currWordType[7].toString()
        val string9 = currWordType[8].toString()
        val string10 = currWordType[9].toString()
        val string11 = currWordType[10].toString()
        val string12 = currWordType[11].toString()
        val string13 = currWordType[12].toString()
        val string14 = currWordType[13].toString()
        val string15 = currWordType[14].toString()
        val string16 = currWordType[15].toString()
        val string17 = currWordType[16].toString()
        val string18 = currWordType[17].toString()
        val string19 = currWordType[18].toString()
        val tweet = Words(
            source =  firstString,
            definition = secondString,
            examples =  thirdString,
            random = string4,
            syllable = string5,
            view5 = string6,
            view6 = string7,
            view7 = string8,
            view8 = string9,
            view9 = string10,
            view10 = string11,
            view11 = string12,
            view12 = string13,
            view13 = string14,
            view14 = string15,
            view15 = string16,
            view16 = string17,
            view17 = string18,
            view18 = string19,
            view19 = "",
            view20 = "",
            view21 = "",
            view22 = "",
            view23 = ""
        )
        tweets.add(tweet)
        return tweets
    }

    fun retrievePartOfWord(apiKey: String, word: String): MutableCollection<Words> {
        val request = Request.Builder()
            // https://rapidapi.com/dpventures/api/wordsapi/
            // I reference  api, url(), get(), addHeader(), build() from above website
            .url("https://wordsapiv1.p.rapidapi.com/words/$word/partOf")
            .get()
            .addHeader("X-RapidAPI-Key", apiKey)
            .build()
        val response = okHttpClient.newCall(request).execute()
        val tweets: MutableCollection<Words> = mutableListOf()
        val responseString: String? = response.body?.string()
        val json: JSONObject = JSONObject(responseString)
        val currWord = json.getString("word")
        val currWordType = json.getJSONArray("partOf")
        val firstString = currWordType[0].toString()
        val secondString = currWordType[1].toString()
        val thirdString = currWordType[2].toString()
        val fourthString = currWordType[3].toString()
        val fifthString = currWordType[4].toString()
        val sixthString = currWordType[5].toString()
        val sevenString = currWordType[6].toString()
        val eightString = currWordType[7].toString()
        val tweet = Words(
            source = eightString,
            definition = firstString,
            examples =  secondString,
            random = thirdString,
            syllable = fourthString,
            view5 = fifthString,
            view6 = sixthString,
            view7 = sevenString,
            view8 = "",
            view9 = "",
            view10 = "",
            view11 = "",
            view12 = "",
            view13 = "",
            view14 = "",
            view15 = "",
            view16 = "",
            view17 = "",
            view18 = "",
            view19 = "",
            view20 = "",
            view21 = "",
            view22 = "",
            view23 = ""
        )
        tweets.add(tweet)
        return tweets
    }

    fun retrieveWordType(apiKey: String, wordType: String): MutableCollection<Words> {
        val request = Request.Builder()
            // https://rapidapi.com/dpventures/api/wordsapi/
            // I reference  api, url(), get(), addHeader(), build() from above website
            .url("https://wordsapiv1.p.rapidapi.com/words/$wordType/hasTypes")
            .get()
            .addHeader("X-RapidAPI-Key", apiKey)
            .build()
        val response = okHttpClient.newCall(request).execute()
        val tweets: MutableCollection<Words> = mutableListOf()
        val responseString: String? = response.body?.string()
        val json: JSONObject = JSONObject(responseString)
        val statuses: JSONArray = json.getJSONArray("hasTypes")
        val firstString: String = statuses[0].toString();
        val secondString: String = statuses[1].toString();
        val thirdString: String = statuses[2].toString();
        val fourthString: String = statuses[3].toString();
        val fifthString: String = statuses[4].toString();
        val sixString: String = statuses[5].toString();
        val sevenString: String = statuses[7].toString();
        val eightString: String = statuses[8].toString();
        val nineString: String = statuses[9].toString();
        val tenString: String = statuses[10].toString();
        val eleString: String = statuses[11].toString();
        val twelveString: String = statuses[12].toString();
        val thirString: String = statuses[13].toString();
        val fourString: String = statuses[14].toString();
        val fifString: String = statuses[15].toString();
        val sixtString: String = statuses[16].toString();
        val seventString: String = statuses[17].toString();
        val eighteString: String = statuses[18].toString();
        val nineteenString: String = statuses[19].toString();
        val tweet = Words(
            source =  fifthString,
            definition = firstString,
            examples =  secondString,
            random = thirdString,
            syllable = fourthString,
            view5 = fifthString,
            view6 = sixString,
            view7 = sevenString,
            view8 = eightString,
            view9 = nineString,
            view10 = tenString,
            view11 = eleString,
            view12 = twelveString,
            view13 = thirString,
            view14 = fourString,
            view15 = fifString,
            view16 = sixtString,
            view17 = seventString,
            view18 = eighteString,
            view19 = nineteenString,
            view20 = "",
            view21 = "",
            view22 = "",
            view23 = "",
        )
        tweets.add(tweet)
        return tweets
    }

    fun retrieveWordSyllables(apiKey: String, syllables: String): MutableCollection<newWord> {
        val request = Request.Builder()
            // https://rapidapi.com/dpventures/api/wordsapi/
            // I reference  api, url(), get(), addHeader(), build() from above website
            .url("https://wordsapiv1.p.rapidapi.com/words/$syllables/syllables")
            .get()
            .addHeader("X-RapidAPI-Key", apiKey)
            .build()
        val response = okHttpClient.newCall(request).execute()
        val tweets: MutableCollection<newWord> = mutableListOf()
        val responseString: String? = response.body?.string()
        val json: JSONObject = JSONObject(responseString)
        val word = json.getString("word")
        val currWord = json.getJSONObject("syllables")
        val currCount = currWord.getString("count")
        val name = currWord.names()[0].toString()
        val names = currWord.names()[1].toString()
        val currSyllable = currWord.getJSONArray("list").toString()
        val tweet = newWord(
            task = "word : $word",
            task1 = "$name : $currCount",
            task2 = "$names : $currSyllable",
            task3 = ""
        )
        tweets.add(tweet)
        return tweets
    }
    fun retrieveWordPronunciation(apiKey: String, pronoun: String): MutableCollection<newWord> {
        val request = Request.Builder()
            // https://rapidapi.com/dpventures/api/wordsapi/
            // I reference  api, url(), get(), addHeader(), build() from above website
            .url("https://wordsapiv1.p.rapidapi.com/words/$pronoun/pronunciation")
            .get()
            .addHeader("X-RapidAPI-Key", apiKey)
            .build()
        val response = okHttpClient.newCall(request).execute()
        val tweets: MutableCollection<newWord> = mutableListOf()
        val responseString: String? = response.body?.string()
        val json: JSONObject = JSONObject(responseString)
        val word = json.getString("word")
        val currWord = json.getJSONObject("pronunciation")
        val pronoun = currWord.getString("all")
        val name = currWord.names()
        val tweet = newWord(
            task = "word : $word",
            task1 = "$name : $pronoun",
            task2 = "",
            task3 = ""
        )
        tweets.add(tweet)
        return tweets
    }
    fun retrieveRhyme(apiKey: String, rhyme: String): MutableCollection<Words> {
        val request = Request.Builder()
            // https://rapidapi.com/dpventures/api/wordsapi/
            // I reference  api, url(), get(), addHeader(), build() from above website
            .url("https://wordsapiv1.p.rapidapi.com/words/$rhyme/rhymes")
            .get()
            .addHeader("X-RapidAPI-Key", apiKey)
            .build()
        val response = okHttpClient.newCall(request).execute()
        val tweets: MutableCollection<Words> = mutableListOf()
        val responseString: String? = response.body?.string()
        val json: JSONObject = JSONObject(responseString)
        val currWord = json.getJSONObject("rhymes")
        val curr = currWord.getJSONArray("all")
        val firstString: String = curr[0].toString();
        val secondString: String = curr[1].toString();
        val thirdString: String = curr[2].toString();
        val fourthString: String = curr[3].toString();
        val fifthString: String = curr[4].toString();
        val sixString: String = curr[5].toString();
        val sevenString: String = curr[6].toString();
        val eightString: String = curr[8].toString();
        val nineString: String = curr[9].toString();
        val tenString: String = curr[10].toString();
        val eleString: String = curr[11].toString();
        val twelveString: String = curr[12].toString();
        val thirString: String = curr[13].toString();
        val fourString: String = curr[14].toString();
        val fifString: String = curr[15].toString();
        val sixtString: String = curr[16].toString();
        val seventString: String = curr[17].toString();
        val eighteString: String = curr[18].toString();
        val nineteenString: String = curr[19].toString();
        val tweltyString: String = curr[20].toString();
        val tweltyoneString: String = curr[21].toString();
        val twoString: String = curr[22].toString();
        val threeString: String = curr[23].toString();
        val threeStrings: String = curr[24].toString();
        val tweet = Words(
            source =  threeStrings,
            definition = firstString,
            examples =  secondString,
            random = thirdString,
            syllable = fourthString,
            view5 = fifthString,
            view6 = sixString,
            view7 = sevenString,
            view8 = eightString,
            view9 = nineString,
            view10 = tenString,
            view11 = eleString,
            view12 = twelveString,
            view13 = thirString,
            view14 = fourString,
            view15 = fifString,
            view16 = sixtString,
            view17 = seventString,
            view18 = eighteString,
            view19 = nineteenString,
            view20 = tweltyString,
            view21 = tweltyoneString,
            view22 = twoString,
            view23 = threeString,
        )
        tweets.add(tweet)
        return tweets
    }
    fun retrieveRegionOf(apiKey: String, RegionOf: String): MutableCollection<Words> {
        val request = Request.Builder()
            // https://rapidapi.com/dpventures/api/wordsapi/
            // I reference  api, url(), get(), addHeader(), build() from above website
            .url("https://wordsapiv1.p.rapidapi.com/words/$RegionOf/regionOf")
            .get()
            .addHeader("X-RapidAPI-Key", apiKey)
            .build()
        val response = okHttpClient.newCall(request).execute()
        val tweets: MutableCollection<Words> = mutableListOf()
        val responseString: String? = response.body?.string()
        val json: JSONObject = JSONObject(responseString)
        val statuses: JSONArray = json.getJSONArray("regionOf")
        val firstString: String = statuses[0].toString();
        val secondString: String = statuses[1].toString();
        val thirdString: String = statuses[2].toString();
        val fourthString: String = statuses[3].toString();
        val fifthString: String = statuses[4].toString();
        val sixString: String = statuses[5].toString();
        val sevenString: String = statuses[7].toString();
        val eightString: String = statuses[8].toString();
        val nineString: String = statuses[9].toString();
        val tenString: String = statuses[10].toString();
        val eleString: String = statuses[11].toString();
        val twelveString: String = statuses[12].toString();
        val thirString: String = statuses[13].toString();
        val fourString: String = statuses[14].toString();
        val fifString: String = statuses[15].toString();
        val sixtString: String = statuses[16].toString();
        val seventString: String = statuses[17].toString();
        val eighteString: String = statuses[18].toString();
        val nineteenString: String = statuses[19].toString();
        val tweltyString: String = statuses[20].toString();
        val tweltyoneString: String = statuses[21].toString();
        val twoString: String = statuses[22].toString();
        val threeString: String = statuses[23].toString();
        val tweet = Words(
            source =  fifthString,
            definition = firstString,
            examples =  secondString,
            random = thirdString,
            syllable = fourthString,
            view5 = fifthString,
            view6 = sixString,
            view7 = sevenString,
            view8 = eightString,
            view9 = nineString,
            view10 = tenString,
            view11 = eleString,
            view12 = twelveString,
            view13 = thirString,
            view14 = fourString,
            view15 = fifString,
            view16 = sixtString,
            view17 = seventString,
            view18 = eighteString,
            view19 = nineteenString,
            view20 = tweltyString,
            view21 = tweltyoneString,
            view22 = twoString,
            view23 = threeString,
        )
        tweets.add(tweet)
        return tweets
    }

    fun retrieveInRegion(apiKey: String, inRegion: String): MutableCollection<newWord> {
        val request = Request.Builder()
            // https://rapidapi.com/dpventures/api/wordsapi/
            // I reference  api, url(), get(), addHeader(), build() from above website
            .url("https://wordsapiv1.p.rapidapi.com/words/$inRegion/inRegion")
            .get()
            .addHeader("X-RapidAPI-Key", apiKey)
            .build()
        val response = okHttpClient.newCall(request).execute()
        val tweets: MutableCollection<newWord> = mutableListOf()
        val responseString: String? = response.body?.string()
        val json: JSONObject = JSONObject(responseString)
        val currWordType = json.getJSONArray("inRegion")
        val firstString = currWordType[0].toString()
        val tweet = newWord(
            task =  firstString,
            task1 = "",
            task2 =  "",
            task3 = "",
        )
        tweets.add(tweet)
        return tweets
    }
    fun retrieveHasUsages(apiKey: String, hasUsages: String): MutableCollection<Words> {
        val request = Request.Builder()
            // https://rapidapi.com/dpventures/api/wordsapi/
            // I reference  api, url(), get(), addHeader(), build() from above website
            .url("https://wordsapiv1.p.rapidapi.com/words/$hasUsages/hasUsages")
            .get()
            .addHeader("X-RapidAPI-Key", apiKey)
            .build()
        val response = okHttpClient.newCall(request).execute()
        val tweets: MutableCollection<Words> = mutableListOf()
        val responseString: String? = response.body?.string()
        val json: JSONObject = JSONObject(responseString)
        val statuses: JSONArray = json.getJSONArray("hasUsages")
        val firstString: String = statuses[0].toString();
        val secondString: String = statuses[1].toString();
        val thirdString: String = statuses[2].toString();
        val fourthString: String = statuses[3].toString();
        val fifthString: String = statuses[4].toString();
        val sixString: String = statuses[5].toString();
        val sevenString: String = statuses[7].toString();
        val eightString: String = statuses[8].toString();
        val nineString: String = statuses[9].toString();
        val tenString: String = statuses[10].toString();
        val eleString: String = statuses[11].toString();
        val tweet = Words(
            source =  fifthString,
            definition = firstString,
            examples =  secondString,
            random = thirdString,
            syllable = fourthString,
            view5 = fifthString,
            view6 = sixString,
            view7 = sevenString,
            view8 = eightString,
            view9 = nineString,
            view10 = tenString,
            view11 = eleString,
            view12 = "",
            view13 = "",
            view14 = "",
            view15 = "",
            view16 = "",
            view17 = "",
            view18 = "",
            view19 = "",
            view20 = "",
            view21 = "",
            view22 = "",
            view23 = "",
        )
        tweets.add(tweet)
        return tweets
    }

    fun retrieveUsageOf(apiKey: String, UsageOf: String): MutableCollection<newWord> {
        val request = Request.Builder()
            // https://rapidapi.com/dpventures/api/wordsapi/
            // I reference  api, url(), get(), addHeader(), build() from above website
            .url("https://wordsapiv1.p.rapidapi.com/words/$UsageOf/usageOf")
            .get()
            .addHeader("X-RapidAPI-Key", apiKey)
            .build()
        val response = okHttpClient.newCall(request).execute()
        val tweets: MutableCollection<newWord> = mutableListOf()
        val responseString: String? = response.body?.string()
        val json: JSONObject = JSONObject(responseString)
        val currWordType = json.getJSONArray("usageOf")
        val firstString = currWordType[0].toString()
        val tweet = newWord(
            task =  firstString,
            task1 = "",
            task2 =  "",
            task3 = "",
        )
        tweets.add(tweet)
        return tweets
    }

    fun retrieveMemberOf(apiKey: String, MemberOf: String): MutableCollection<newWord> {
        val request = Request.Builder()
            // https://rapidapi.com/dpventures/api/wordsapi/
            // I reference  api, url(), get(), addHeader(), build() from above website
            .url("https://wordsapiv1.p.rapidapi.com/words/$MemberOf/memberOf")
            .get()
            .addHeader("X-RapidAPI-Key", apiKey)
            .build()
        val response = okHttpClient.newCall(request).execute()
        val tweets: MutableCollection<newWord> = mutableListOf()
        val responseString: String? = response.body?.string()
        val json: JSONObject = JSONObject(responseString)
        val currWordType = json.getJSONArray("memberOf")
        val firstString = currWordType[0].toString()
        val secondString = currWordType[1].toString()
        val tweet = newWord(
            task =  firstString,
            task1 = secondString,
            task2 =  "",
            task3 = "",
        )
        tweets.add(tweet)
        return tweets
    }

    fun retrieveHasMember(apiKey: String, HasMember: String): MutableCollection<newWord> {
        val request = Request.Builder()
            // https://rapidapi.com/dpventures/api/wordsapi/
            // I reference  api, url(), get(), addHeader(), build() from above website
            .url("https://wordsapiv1.p.rapidapi.com/words/$HasMember/hasMembers")
            .get()
            .addHeader("X-RapidAPI-Key", apiKey)
            .build()
        val response = okHttpClient.newCall(request).execute()
        val tweets: MutableCollection<newWord> = mutableListOf()
        val responseString: String? = response.body?.string()
        val json: JSONObject = JSONObject(responseString)
        val currWordType = json.getJSONArray("hasMembers")
        val firstString = currWordType[0].toString()
        val tweet = newWord(
            task =  firstString,
            task1 = "",
            task2 =  "",
            task3 = "",
        )
        tweets.add(tweet)
        return tweets
    }

    fun retrieveHasCategories(apiKey: String, HasCategories: String): MutableCollection<Words> {
        val request = Request.Builder()
            // https://rapidapi.com/dpventures/api/wordsapi/
            // I reference  api, url(), get(), addHeader(), build() from above website
            .url("https://wordsapiv1.p.rapidapi.com/words/$HasCategories/hasCategories")
            .get()
            .addHeader("X-RapidAPI-Key", apiKey)
            .build()
        val response = okHttpClient.newCall(request).execute()
        val tweets: MutableCollection<Words> = mutableListOf()
        val responseString: String? = response.body?.string()
        val json: JSONObject = JSONObject(responseString)
        val statuses: JSONArray = json.getJSONArray("hasCategories")
        val firstString: String = statuses[0].toString();
        val secondString: String = statuses[1].toString();
        val thirdString: String = statuses[2].toString();
        val fourthString: String = statuses[3].toString();
        val fifthString: String = statuses[4].toString();
        val sixString: String = statuses[5].toString();
        val sevenString: String = statuses[7].toString();
        val eightString: String = statuses[8].toString();
        val nineString: String = statuses[9].toString();
        val tenString: String = statuses[10].toString();
        val eleString: String = statuses[11].toString();
        val twelveString: String = statuses[12].toString();
        val thirString: String = statuses[13].toString();
        val fourString: String = statuses[14].toString();
        val fifString: String = statuses[15].toString();
        val sixtString: String = statuses[16].toString();
        val seventString: String = statuses[17].toString();
        val eighteString: String = statuses[18].toString();
        val nineteenString: String = statuses[19].toString();
        val tweltyString: String = statuses[20].toString();
        val tweltyoneString: String = statuses[21].toString();
        val twoString: String = statuses[22].toString();
        val threeString: String = statuses[23].toString();
        val tweet = Words(
            source =  fifthString,
            definition = firstString,
            examples =  secondString,
            random = thirdString,
            syllable = fourthString,
            view5 = fifthString,
            view6 = sixString,
            view7 = sevenString,
            view8 = eightString,
            view9 = nineString,
            view10 = tenString,
            view11 = eleString,
            view12 = twelveString,
            view13 = thirString,
            view14 = fourString,
            view15 = fifString,
            view16 = sixtString,
            view17 = seventString,
            view18 = eighteString,
            view19 = nineteenString,
            view20 = tweltyString,
            view21 = tweltyoneString,
            view22 = twoString,
            view23 = threeString,
        )
        tweets.add(tweet)
        return tweets
    }

    fun retrieveInCategory(apiKey: String, InCategory: String): MutableCollection<newWord> {
        val request = Request.Builder()
            // https://rapidapi.com/dpventures/api/wordsapi/
            // I reference  api, url(), get(), addHeader(), build() from above website
            .url("https://wordsapiv1.p.rapidapi.com/words/$InCategory/inCategory")
            .get()
            .addHeader("X-RapidAPI-Key", apiKey)
            .build()
        val response = okHttpClient.newCall(request).execute()
        val tweets: MutableCollection<newWord> = mutableListOf()
        val responseString: String? = response.body?.string()
        val json: JSONObject = JSONObject(responseString)
        val currWordType = json.getJSONArray("inCategory")
        val firstString = currWordType[0].toString()
        val secondString = currWordType[1].toString()
        val tweet = newWord(
            task =  firstString,
            task1 = secondString,
            task2 =  "",
            task3 = "",
        )
        tweets.add(tweet)
        return tweets
    }
    fun retrieveIsATypeOf(apiKey: String, word: String): MutableCollection<Words> {
        val request = Request.Builder()
            // https://rapidapi.com/dpventures/api/wordsapi/
            // I reference  api, url(), get(), addHeader(), build() from above website
            .url("https://wordsapiv1.p.rapidapi.com/words/$word/typeOf")
            .get()
            .addHeader("X-RapidAPI-Key", apiKey)
            .build()
        val response = okHttpClient.newCall(request).execute()
        val tweets: MutableCollection<Words> = mutableListOf()
        val responseString: String? = response.body?.string()
        val json: JSONObject = JSONObject(responseString)
        val currWordType = json.getJSONArray("typeOf")
        val firstString = currWordType[0].toString()
        val secondString = currWordType[1].toString()
        val thirdString = currWordType[2].toString()
        val fourthString = currWordType[3].toString()
        val fifthString = currWordType[4].toString()
        val sixString = currWordType[5].toString()
        val tweet = Words(
            source = firstString,
            definition = secondString,
            examples = thirdString,
            random = fourthString,
            syllable = fifthString,
            view5 = sixString,
            view6 = "",
            view7 = "",
            view8 = "",
            view9 = "",
            view10 = "",
            view11 = "",
            view12 = "",
            view13 = "",
            view14 = "",
            view15 = "",
            view16 = "",
            view17 = "",
            view18 = "",
            view19 = "",
            view20 = "",
            view21 = "",
            view22 = "",
            view23 = ""
        )
        tweets.add(tweet)
        return tweets
    }
    fun retrieveHasSubstance(apiKey: String, word: String): MutableCollection<Words> {
        val request = Request.Builder()
            // https://rapidapi.com/dpventures/api/wordsapi/
            // I reference  api, url(), get(), addHeader(), build() from above website
            .url("https://wordsapiv1.p.rapidapi.com/words/$word/hasSubstances")
            .get()
            .addHeader("X-RapidAPI-Key", apiKey)
            .build()
        val response = okHttpClient.newCall(request).execute()
        val tweets: MutableCollection<Words> = mutableListOf()
        val responseString: String? = response.body?.string()
        val json: JSONObject = JSONObject(responseString)
        val statuses: JSONArray = json.getJSONArray("hasSubstances")
        val firstString: String = statuses[0].toString();
        val secondString: String = statuses[1].toString();
        val thirdString: String = statuses[2].toString();
        val fourthString: String = statuses[3].toString();
        val fifthString: String = statuses[4].toString();
        val sixString: String = statuses[5].toString();
        val tweet = Words(
            source =  firstString,
            definition = secondString,
            examples =  thirdString,
            random = fourthString,
            syllable = fifthString,
            view5 = sixString,
            view6 = "",
            view7 = "",
            view8 = "",
            view9 = "",
            view10 = "",
            view11 = "",
            view12 = "",
            view13 = "",
            view14 = "",
            view15 = "",
            view16 = "",
            view17 = "",
            view18 = "",
            view19 = "",
            view20 = "",
            view21 = "",
            view22 = "",
            view23 = "",
        )
        tweets.add(tweet)
        return tweets
    }
    fun retrieveSubstanceOf(apiKey: String, SubstanceOf: String): MutableCollection<newWord> {
        val request = Request.Builder()
            // https://rapidapi.com/dpventures/api/wordsapi/
            // I reference  api, url(), get(), addHeader(), build() from above website
            .url("https://wordsapiv1.p.rapidapi.com/words/$SubstanceOf/substanceOf")
            .get()
            .addHeader("X-RapidAPI-Key", apiKey)
            .build()
        val response = okHttpClient.newCall(request).execute()
        val tweets: MutableCollection<newWord> = mutableListOf()
        val responseString: String? = response.body?.string()
        val json: JSONObject = JSONObject(responseString)
        val currWordType = json.getJSONArray("substanceOf")
        val firstString = currWordType[0].toString()
        val secondString = currWordType[1].toString()
        val thirdString = currWordType[2].toString()
        val fourthString = currWordType[3].toString()
        val tweet = newWord(
            task =  firstString,
            task1 = secondString,
            task2 =  thirdString,
            task3 = fourthString,
        )
        tweets.add(tweet)
        return tweets
    }
    fun retrieveAlso(apiKey: String, also: String): MutableCollection<newWord> {
        val request = Request.Builder()
            // https://rapidapi.com/dpventures/api/wordsapi/
            // I reference  api, url(), get(), addHeader(), build() from above website
            .url("https://wordsapiv1.p.rapidapi.com/words/$also/also")
            .get()
            .addHeader("X-RapidAPI-Key", apiKey)
            .build()
        val response = okHttpClient.newCall(request).execute()
        val tweets: MutableCollection<newWord> = mutableListOf()
        val responseString: String? = response.body?.string()
        val json: JSONObject = JSONObject(responseString)
        val currWordType = json.getJSONArray("also")
        val firstString = currWordType[0].toString()
        val tweet = newWord(
            task =  firstString,
            task1 = "",
            task2 =  "",
            task3 = "",
        )
        tweets.add(tweet)
        return tweets
    }
    fun retrieveSimilarTo(apiKey: String, similarTo: String): MutableCollection<Words> {
        val request = Request.Builder()
            // https://rapidapi.com/dpventures/api/wordsapi/
            // I reference  api, url(), get(), addHeader(), build() from above website
            .url("https://wordsapiv1.p.rapidapi.com/words/$similarTo/similarTo")
            .get()
            .addHeader("X-RapidAPI-Key", apiKey)
            .build()
        val response = okHttpClient.newCall(request).execute()
        val tweets: MutableCollection<Words> = mutableListOf()
        val responseString: String? = response.body?.string()
        val json: JSONObject = JSONObject(responseString)
        val statuses: JSONArray = json.getJSONArray("similarTo")
        val firstString: String = statuses[0].toString();
        val secondString: String = statuses[1].toString();
        val thirdString: String = statuses[2].toString();
        val fourthString: String = statuses[3].toString();
        val fifthString: String = statuses[4].toString();
        val sixString: String = statuses[5].toString();
        val sevenString: String = statuses[7].toString();
        val eightString: String = statuses[8].toString();
        val nineString: String = statuses[9].toString();
        val tenString: String = statuses[10].toString();
        val eleString: String = statuses[11].toString();
        val twelveString: String = statuses[12].toString();
        val thirString: String = statuses[13].toString();
        val fourString: String = statuses[14].toString();
        val tweet = Words(
            source =  fifthString,
            definition = firstString,
            examples =  secondString,
            random = thirdString,
            syllable = fourthString,
            view5 = fifthString,
            view6 = sixString,
            view7 = sevenString,
            view8 = eightString,
            view9 = nineString,
            view10 = tenString,
            view11 = eleString,
            view12 = twelveString,
            view13 = thirString,
            view14 = fourString,
            view15 = "",
            view16 = "",
            view17 = "",
            view18 = "",
            view19 = "",
            view20 = "",
            view21 = "",
            view22 = "",
            view23 = "",
        )
        tweets.add(tweet)
        return tweets
    }
    fun retrieveEntail(apiKey: String, entail: String): MutableCollection<newWord> {
        val request = Request.Builder()
            // https://rapidapi.com/dpventures/api/wordsapi/
            // I reference  api, url(), get(), addHeader(), build() from above website
            .url("https://wordsapiv1.p.rapidapi.com/words/$entail/entails")
            .get()
            .addHeader("X-RapidAPI-Key", apiKey)
            .build()
        val response = okHttpClient.newCall(request).execute()
        val tweets: MutableCollection<newWord> = mutableListOf()
        val responseString: String? = response.body?.string()
        val json: JSONObject = JSONObject(responseString)
        val currWordType = json.getJSONArray("entails")
        val firstString = currWordType[0].toString()
        val tweet = newWord(
            task =  firstString,
            task1 = "",
            task2 =  "",
            task3 = "",
        )
        tweets.add(tweet)
        return tweets
    }
    fun retrieveHasPart(apiKey: String, HasPart: String): MutableCollection<Words> {
        val request = Request.Builder()
            // https://rapidapi.com/dpventures/api/wordsapi/
            // I reference  api, url(), get(), addHeader(), build() from above website
            .url("https://wordsapiv1.p.rapidapi.com/words/$HasPart/hasParts")
            .get()
            .addHeader("X-RapidAPI-Key", apiKey)
            .build()
        val response = okHttpClient.newCall(request).execute()
        val tweets: MutableCollection<Words> = mutableListOf()
        val responseString: String? = response.body?.string()
        val json: JSONObject = JSONObject(responseString)
        val statuses: JSONArray = json.getJSONArray("hasParts")
        val firstString: String = statuses[0].toString();
        val secondString: String = statuses[1].toString();
        val thirdString: String = statuses[2].toString();
        val fourthString: String = statuses[3].toString();
        val fifthString: String = statuses[4].toString();
        val sixString: String = statuses[5].toString();
        val sevenString: String = statuses[7].toString();
        val eightString: String = statuses[8].toString();
        val nineString: String = statuses[9].toString();
        val tenString: String = statuses[10].toString();
        val eleString: String = statuses[11].toString();
        val twelveString: String = statuses[12].toString();
        val thirString: String = statuses[13].toString();
        val fourString: String = statuses[14].toString();
        val fifteenString: String = statuses[15].toString();
        val sixteenString: String = statuses[16].toString();
        val seventeenString: String = statuses[17].toString();
        val tweet = Words(
            source =  fifthString,
            definition = firstString,
            examples =  secondString,
            random = thirdString,
            syllable = fourthString,
            view5 = fifthString,
            view6 = sixString,
            view7 = sevenString,
            view8 = eightString,
            view9 = nineString,
            view10 = tenString,
            view11 = eleString,
            view12 = twelveString,
            view13 = thirString,
            view14 = fourString,
            view15 = fifteenString,
            view16 = sixteenString,
            view17 = seventeenString,
            view18 = "",
            view19 = "",
            view20 = "",
            view21 = "",
            view22 = "",
            view23 = "",
        )
        tweets.add(tweet)
        return tweets
    }

    fun retrieveIsAnInstance(apiKey: String, IsAnInstance: String): MutableCollection<newWord> {
        val request = Request.Builder()
            // https://rapidapi.com/dpventures/api/wordsapi/
            // I reference  api, url(), get(), addHeader(), build() from above website
            .url("https://wordsapiv1.p.rapidapi.com/words/$IsAnInstance/instanceOf")
            .get()
            .addHeader("X-RapidAPI-Key", apiKey)
            .build()
        val response = okHttpClient.newCall(request).execute()
        val tweets: MutableCollection<newWord> = mutableListOf()
        val responseString: String? = response.body?.string()
        val json: JSONObject = JSONObject(responseString)
        val statuses: JSONArray = json.getJSONArray("instanceOf")
        val firstString: String = statuses[0].toString();
        val tweet = newWord(
            task = firstString,
            task1 = "",
            task2 = "",
            task3 = ""
        )
        tweets.add(tweet)
        return tweets
    }
    @SuppressLint("SuspiciousIndentation")
    fun retrieveHasInstances(apiKey: String, HasInstances: String): MutableCollection<Words> {
        val request = Request.Builder()
            // https://rapidapi.com/dpventures/api/wordsapi/
            // I reference  api, url(), get(), addHeader(), build() from above website
            .url("https://wordsapiv1.p.rapidapi.com/words/$HasInstances/hasInstances")
            .get()
            .addHeader("X-RapidAPI-Key", apiKey)
            .build()
        val response = okHttpClient.newCall(request).execute()
        val tweets: MutableCollection<Words> = mutableListOf()
        val responseString: String? = response.body?.string()
        val json: JSONObject = JSONObject(responseString)
        val statuses: JSONArray = json.getJSONArray("hasInstances")
        val firstString: String = statuses[0].toString();
        val secondString: String = statuses[1].toString();
        val thirdString: String = statuses[2].toString();
        val fourthString: String = statuses[3].toString();
        val fifthString: String = statuses[4].toString();
        val sixString: String = statuses[5].toString();
        val sevenString: String = statuses[7].toString();
        val eightString: String = statuses[8].toString();
        val nineString: String = statuses[9].toString();
        val tenString: String = statuses[10].toString();
        val eleString: String = statuses[11].toString();
        val twelveString: String = statuses[12].toString();
        val thirString: String = statuses[13].toString();
        val fourString: String = statuses[14].toString();
        val fifString: String = statuses[15].toString();
        val sixtString: String = statuses[16].toString();
        val seventString: String = statuses[17].toString();
        val eighteString: String = statuses[18].toString();
        val nineteenString: String = statuses[19].toString();
        val tweltyString: String = statuses[20].toString();
        val tweltyoneString: String = statuses[21].toString();
        val twoString: String = statuses[22].toString();
        val threeString: String = statuses[23].toString();
        val tweet = Words(
            source =  fifthString,
            definition = firstString,
            examples =  secondString,
            random = thirdString,
            syllable = fourthString,
            view5 = fifthString,
            view6 = sixString,
            view7 = sevenString,
            view8 = eightString,
            view9 = nineString,
            view10 = tenString,
            view11 = eleString,
            view12 = twelveString,
            view13 = thirString,
            view14 = fourString,
            view15 = fifString,
            view16 = sixtString,
            view17 = seventString,
            view18 = eighteString,
            view19 = nineteenString,
            view20 = tweltyString,
            view21 = tweltyoneString,
            view22 = twoString,
            view23 = threeString,
        )
        tweets.add(tweet)
        return tweets
    }
}