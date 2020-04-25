package com.news.elvezanews.Data;

import com.news.elvezanews.Models.NewsModelList;

import java.util.ArrayList;
import java.util.List;

public class LoadTempNews {

    private List<NewsModelList> newslist;
    private NewsModelList news;

    public LoadTempNews() {
    loadNews();

    }

    private void loadNews() {

        newslist = new ArrayList<>();
        newslist.add( new NewsModelList("1", "05:00", "#Covid19Update by President Uhuru Kenyatta (9 more cases, 234 total)", "Nine more people have tested positive for Covid-19 bringing the number to 234 in Kenya. So far, 53 people have been discharged and 11 lost their lives. \n" +
                "\n" +
                "President Kenyatta directed the Ministry of Health to come-up with a welfare package to cushion medical officers as they deal with the pandemic. \n" +
                "\n" +
                "He also stated that his government had identified households in Nairobi that will receive stipends during the pandemic. ", "https://www.musicinafrica.net/sites/default/files/styles/article_slider_large/public/images/article/202001/president.jpg?itok=XPWS5hVw", "nSlWqVvvzic","", "Nature" ));

        newslist.add( new NewsModelList("2", "10:15", "Coronavirus is changing Life as we know It", "Vaccines are seen as the holy grail. If the US population can be successfully vaccinated for the coronavirus, that would make it easier for the country to reopen.\n" +
                "Kizzmekia Corbett, NIH's lead scientist for coronavirus vaccine research, told CNN that a vaccine could be ready in the fall for \"emergency use.\"\n\n" +
                "\"That would be for healthcare workers and people who might be in constant contact and risk of being exposed over and over,\" she said. \"And then for the general population our target goal is for next spring,\" she added, assuming all goes well.\n\n\n" +
                "Other researchers are skeptical, saying the type of vaccine she's referring to has not been successful in humans.\n\n" +
                "Several companies are testing vaccines, but it will take months -- or more likely at least a year -- to complete those trials.", "https://www.sueddeutsche.de/image/sz.1.4798182?v=1581694916 ", "sHP0UIdZyI4", "","Health"));

        newslist.add( new NewsModelList("3", "10:15", "What Bill Gates is afraid of", "There's something out there that's as bad as war, something that kills as many people as war, and Bill Gates doesn't think we're ready for it.\n" +
                "\n" +
                "\"Look at the death chart of the 20th century,\" he says, because he's the kind of guy that looks at death charts. \"I think everybody would say there must be a spike for World War I. Sure enough, there it is, like 25 million. And there must be a big spike for World War II, and there it is, it's like 65 million. But then you'll see this other spike that is as large as World War II right after World War I, and most people, would say, 'What was that?'\"\n" +
                "\n" +
                "\"Well, that was the Spanish flu.\"\n" +
                "\n" +
                "In a 1990 paper on \"The Anthropology of Infectious Disease,\" Marcia Inhorn and Peter Brown estimated that infectious diseases \"have likely claimed more lives than all wars, noninfectious diseases, and natural disasters put together.\" \n" +
                "\n" +
                "Infectious diseases are our oldest, deadliest foe.", "https://www.incimages.com/uploaded_files/image/970x450/getty_543265518_418238.jpg", "9AEMKudv5p0","", "Health"));

        newslist.add( new NewsModelList("4", "10:15", "Coronavirus: UK lockdown extended for at least 3 weeks", "The daily press conference was given by the first secretary of state, Dominic Raab, who is standing in for the prime minister as he continues his recovery from coronavirus.\\n\" +\n" +
                "                \"\\n\" +\n" +
                "                \"He was joined by the chief medical officer, Prof Chris Whitty, and Sir Patrick Vallance, the government's chief scientific adviser. Here's what they told us:\\n\" +\n" +
                "                \"\\n\" +\n" +
                "                \"    The rate of infection in the community is now likely to be below one (meaning on average each infected person passes the virus to fewer than one other people) - but it may be higher in other settings, such as care homes.\\n\" +\n" +
                "                \"    The UK's lockdown will continue for at least another three weeks, as the government aims to avoid a second peak in infections.\\n\" +\n" +
                "                \"    The number of new cases is stabilising, and perhaps starting to fall. The same is true of hospital admissions. This will only continue if the public continues with social distancing.\\n\" +\n" +
                "                \"    The government is conducting a review of whether people from non-white backgrounds are disproportionately affected by coronavirus, but it is not yet certain that this is the case.\\n\" +\n" +
                "                \"    The public should continue to use A&E for non-coronavirus emergencies, as there is adequate capacity.\\n", "https://ichef.bbci.co.uk/news/976/cpsprodpb/D909/production/_111316555_reord.png", "UBuB6Z_sa8w","", "Health"));

        newslist.add( new NewsModelList("4", "10:15", "Coronavirus: UK lockdown extended for at least 3 weeks", "", "https://cdn.images.express.co.uk/img/dynamic/78/590x/1172342_1.jpg", "UBuB6Z_sa8w","", "Health"));
        newslist.add( new NewsModelList("4", "10:15", "Coronavirus: UK lockdown extended for at least 3 weeks", "", "https://cdn.images.express.co.uk/img/dynamic/78/590x/1172342_1.jpg", "UBuB6Z_sa8w","", "Health"));

        newslist.add( new NewsModelList("4", "10:15", "Coronavirus: UK lockdown extended for at least 3 weeks", "", "https://cdn.images.express.co.uk/img/dynamic/78/590x/1172342_1.jpg", "UBuB6Z_sa8w","", "Health"));

        newslist.add( new NewsModelList("4", "10:15", "Coronavirus: UK lockdown extended for at least 3 weeks", "", "https://cdn.images.express.co.uk/img/dynamic/78/590x/1172342_1.jpg", "UBuB6Z_sa8w","", "Health"));

    }


    public List<NewsModelList> getNews() {
        return newslist;
    }
}
