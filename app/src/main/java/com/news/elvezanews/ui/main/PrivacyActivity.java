package com.news.elvezanews.ui.main;

import android.os.Bundle;
import android.os.strictmode.WebViewMethodCalledOnWrongThreadViolation;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.news.elvezanews.R;

public class PrivacyActivity extends AppCompatActivity {
   // private TextView privacyBody;
    private FloatingActionButton back;
    private TextView privacyBody;
    private String p1;
    private String p2;
    private String p3;
    private String p4;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.privacy);
        back = findViewById(R.id.backbutton);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        privacyBody = findViewById(R.id.privacy_body);
        createPrivateStrings();

        privacyBody.append(Html.fromHtml(p1));
        privacyBody.append(Html.fromHtml(p2));
        privacyBody.append(Html.fromHtml(p3));
        privacyBody.append(Html.fromHtml(p4));

    }

    private void createPrivateStrings(){
        p1 = "<span>\n" +
                "<p>updated May 22, 2020</p>\n" +
                "<br/>\n" +
                "<p>\n" +
                "    Thank you for choosing to be part of our community at Elveza Media, doing business as Elveza\n" +
                "    Media. We are committed to protecting your personal information and your right to privacy. If you\n" +
                "    have any questions or concerns about our policy, or our practices with regards to your personal\n" +
                "    information, please contact us at elvezamedia@gmail.com.\n" +
                "</p>\n" +
                "<p>\n" +
                "    When you visit our mobile application, and use our services, you trust us with your personal\n" +
                "    information. We take your privacy very seriously. In this privacy policy, we seek to explain to you in\n" +
                "    the clearest way possible what information we collect, how we use it and what rights you have in\n" +
                "    relation to it. We hope you take some time to read through it carefully, as it is important. If there are\n" +
                "    any terms in this privacy policy that you do not agree with, please discontinue use of our Apps and\n" +
                "    our services.\n" +
                "\n" +
                "</p>\n" +
                "<p>\n" +
                "    This privacy policy applies to all information collected through our mobile application, (\"Apps\"),\n" +
                "    and/or any related services, sales, marketing or events (we refer to them collectively in this privacy\n" +
                "    policy as the \"Services\").\n" +
                "</p>\n" +
                "<p>\n" +
                "    Please read this privacy policy carefully as it will help you make informed decisions about sharing\n" +
                "    your personal information with us.\n" +
                "</p>\n" +
                "</span> \n" +
                "<span>\n" +
                "<p><h3>TABLE OF CONTENTS</h1></p>\n" +
                "    <p> \n" +
                "        <ul style=\"display: block;text-decoration: underline;\">1. WHAT INFORMATION DO WE COLLECT?</ul>\n" +
                "        <ul style=\"display: block;text-decoration: underline;\">2. HOW DO WE USE YOUR INFORMATION?</ul>\n" +
                "        <ul style=\"display: block;text-decoration: underline;\">3. WILL YOUR INFORMATION BE SHARED WITH ANYONE?</ul>\n" +
                "        <ul style=\"display: block;text-decoration: underline;\">4. HOW DO WE HANDLE YOUR SOCIAL LOGINS?</ul>\n" +
                "        <ul style=\"display: block;text-decoration: underline;\">5. WHAT IS OUR STANCE ON THIRD-PARTY WEBSITES?</ul>\n" +
                "        <ul style=\"display: block;text-decoration: underline;\">6. HOW LONG DO WE KEEP YOUR INFORMATION?</ul>\n" +
                "        <ul style=\"display: block;text-decoration: underline;\">7. HOW DO WE KEEP YOUR INFORMATION SAFE?</ul>\n" +
                "        <ul style=\"display: block;text-decoration: underline;\">9. WHAT ARE YOUR PRIVACY RIGHTS?</ul>\n" +
                "        <ul style=\"display: block;text-decoration: underline;\">10. CONTROLS FOR DO-NOT-TRACK FEATURES</ul>\n" +
                "        <ul style=\"display: block;text-decoration: underline;\">11. SPECIFIC PRIVACY RIGHTS ON THOSE UNDER 18 YEARS OLD?</ul>\n" +
                "        <ul style=\"display: block;text-decoration: underline;\">12. DO WE MAKE UPDATES TO THIS POLICY?</ul>\n" +
                "        <ul style=\"display: block;text-decoration: underline;\">13. HOW CAN YOU CONTACT US ABOUT THIS POLICY?</ul>\n" +
                "    </p>\n" +
                "</span>\n" +
                "<span>\n" +
                "    <p><h3>1. WHAT INFORMATION DO WE COLLECT?</h3></p>\n" +
                "    <br/>\n" +
                "    <p><h4>Personal information you disclose to us</h4></p>\n" +
                "    <p><i>In Short: We collect personal information that you provide to us.</i></p>\n" +
                "    <p>We collect personal information that you voluntarily provide to us when expressing an\n" +
                "    interest in obtaining information about us or our products and services, when participating in\n" +
                "    activities on the Apps or otherwise contacting us.</p>\n" +
                "\n" +
                "    <p>The personal information that we collect depends on the context of your interactions with us and the\n" +
                "    Apps, the choices you make and the products and features you use. The personal information we\n" +
                "    collect can include the following:</p>\n" +
                "    \n" +
                "    <p>Social Media Login Data. We may provide you with the option to register using social media account\n" +
                "    details, like your Facebook, Twitter or other social media account. If you choose to register in this\n" +
                "    way, we will collect the Information described in the section called \"HOW DO WE HANDLE YOUR\n" +
                "    SOCIAL LOGINS\" below.</p>\n" +
                "\n" +
                "    <p>All personal information that you provide to us must be true, complete and accurate, and you must\n" +
                "    notify us of any changes to such personal information.</p>\n" +
                "    <br/>\n" +
                "    <p><h4>Information automatically collected</h4></p>\n" +
                "    <p>In Short: Some information — such as IP address and/or browser and device characteristics — is\n" +
                "    collected automatically when you visit our Apps.</p>\n" +
                "\n" +
                "    <p>We automatically collect certain information when you visit, use or navigate the Apps. This\n" +
                "    information does not reveal your specific identity (like your name or contact information) but may\n" +
                "    include device and usage information, such as your IP address, browser and device characteristics,\n" +
                "    operating system, language preferences, referring URLs, device name, country, location, information\n" +
                "    about how and when you use our Apps and other technical information. This information is primarily\n" +
                "    needed to maintain the security and operation of our Apps, and for our internal analytics and\n" +
                "    reporting purposes.</p>\n" +
                "    \n" +
                "    <p><h4>Information collected through our Apps</h4></p>\n" +
                "    <p><i>In Short: We may collect information regarding your geo-location, mobile device, push notifications,\n" +
                "    when you use our apps.</i></p>\n" +
                "    <p>If you use our Apps, we may also collect the following information:</p>\n" +
                "    <ul>\n" +
                "    <li>Geo-Location Information. We may request access or permission to and track location-based\n" +
                "    information from your mobile device, either continuously or while you are using our mobile\n" +
                "    application, to provide location-based services. If you wish to change our access or\n" +
                "    permissions, you may do so in your device's settings.</li><br/>\n" +
                "    <li>Mobile Device Access. We may request access or permission to certain features from your\n" +
                "    mobile device, including your mobile device's and other features. If you wish to change our\n" +
                "    access or permissions, you may do so in your device's settings.</li><br/>\n" +
                "    <li>Mobile Device Data. We may automatically collect device information (such as your mobile\n" +
                "    device ID, model and manufacturer), operating system, version information and IP address.</li><br/>\n" +
                "    <li>Push Notifications. We may request to send you push notifications regarding your account or\n" +
                "    the mobile application. If you wish to opt-out from receiving these types of communications,\n" +
                "    you may turn them off in your device's settings.</li><br/>\n" +
                "    </ul>\n" +
                "\n" +
                "</span>";
    p2 = "<span>\n" +
            "    <p><h4>2. HOW DO WE USE YOUR INFORMATION?</h4></p>\n" +
            "    <p><i>In Short: We process your information for purposes based on legitimate business interests, the\n" +
            "    fulfillment of our contract with you, compliance with our legal obligations, and/or your consent.</i></p>\n" +
            "    <p>We use personal information collected via our Apps for a variety of business purposes described\n" +
            "    below. We process your personal information for these purposes in reliance on our legitimate\n" +
            "    business interests, in order to enter into or perform a contract with you, with your consent, and/or for\n" +
            "    compliance with our legal obligations. We indicate the specific processing grounds we rely on next to\n" +
            "    each purpose listed below.</p>\n" +
            "    <p>\n" +
            "    We use the information we collect or receive:\n" +
            "    <ul>\n" +
            "    <li>     To send you marketing and promotional communications. We and/or our third party\n" +
            "    marketing partners may use the personal information you send to us for our marketing\n" +
            "    purposes, if this is in accordance with your marketing preferences. You can opt-out of our\n" +
            "    marketing emails at any time (see the \"WHAT ARE YOUR PRIVACY RIGHTS\" below).</li><br/>\n" +
            "    <li>     Deliver targeted advertising to you. We may use your information to develop and display\n" +
            "    content and advertising (and work with third parties who do so) tailored to your interests\n" +
            "    and/or location and to measure its effectiveness.</li><br/>\n" +
            "    <li>     To deliver services to the user. We may use your information to provide you with the\n" +
            "    requested service.</li><br/>\n" +
            "    <li>     For other Business Purposes. We may use your information for other Business Purposes,\n" +
            "    such as data analysis, identifying usage trends, determining the effectiveness of our\n" +
            "    promotional campaigns and to evaluate and improve our Apps, products, marketing and your\n" +
            "    experience. We may use and store this information in aggregated and anonymized form so\n" +
            "    that it is not associated with individual end users and does not include personal information.\n" +
            "    We will not use identifiable personal information without your consent.</li><br/>\n" +
            "    </ul>\n" +
            "</p>\n" +
            "</span>\n" +
            "\n" +
            "<span>\n" +
            "    <p><h4>3. WILL YOUR INFORMATION BE SHARED WITH ANYONE?</h4></p>\n" +
            "    <p><i>In Short: We only share information with your consent, to comply with laws, to provide you with\n" +
            "services, to protect your rights, or to fulfill business obligations.</i></p>\n" +
            "<p>\n" +
            "\n" +
            "We may process or share data based on the following legal basis:\n" +
            "<ul>\n" +
            "<li>: We may process your data if you have given us specific consent to use your\n" +
            "personal information in a specific purpose.</li>\n" +
            "<li>Legitimate Interests: We may process your data when it is reasonably necessary to\n" +
            "achieve our legitimate business interests.</li>\n" +
            "<li>Performance of a Contract: Where we have entered into a contract with you, we may\n" +
            "process your personal information to fulfill the terms of our contract.</li>\n" +
            "<li>Legal Obligations: We may disclose your information where we are legally required to\n" +
            "do so in order to comply with applicable law, governmental requests, a judicial\n" +
            "proceeding, court order, or legal process, such as in response to a court order or a\n" +
            "subpoena (including in response to public authorities to meet national security or law\n" +
            "enforcement requirements).</li>\n" +
            "<li>Vital Interests: We may disclose your information where we believe it is necessary to\n" +
            "investigate, prevent, or take action regarding potential violations of our policies, suspected\n" +
            "fraud, situations involving potential threats to the safety of any person and illegal activities, or\n" +
            "as evidence in litigation in which we are involved.</li>\n" +
            "</ul>\n" +
            "More specifically, we may need to process your data or share your personal information in the\n" +
            "following situations:\n" +
            "<ul>\n" +
            "<li>Vendors, Consultants and Other Third-Party Service Providers: We may share your data with\n" +
            "third party vendors, service providers, contractors or agents who perform services for us or\n" +
            "on our behalf and require access to such information to do that work. Examples include:\n" +
            "payment processing, data analysis, email delivery, hosting services, customer service and\n" +
            "marketing efforts. We may allow selected third parties to use tracking technology on the\n" +
            "Apps, which will enable them to collect data about how you interact with the Apps over time.\n" +
            "This information may be used to, among other things, analyze and track data, determine the\n" +
            "popularity of certain content and better understand online activity. Unless described in this\n" +
            "Policy, we do not share, sell, rent or trade any of your information with third parties for their\n" +
            "promotional purposes.</li>\n" +
            "<li>Business Transfers: We may share or transfer your information in connection with, or during\n" +
            "negotiations of, any merger, sale of company assets, financing, or acquisition of all or a\n" +
            "portion of our business to another company.</li>\n" +
            "<li> Third-Party Advertisers. We may use third-party advertising companies to serve ads when\n" +
            "you visit the Apps. These companies may use information about your visits to our Website(s)\n" +
            "and other websites that are contained in web cookies and other tracking technologies in\n" +
            "order to provide advertisements about goods and services of interest to you.</li>\n" +
            "<li> Affiliates: We may share your information with our affiliates, in which case we will require\n" +
            "those affiliates to honor this privacy policy. Affiliates include our parent company and any\n" +
            "subsidiaries, joint venture partners or other companies that we control or that are under\n" +
            "common control with us.</li>\n" +
            "</ul>\n" +
            "</p>\n" +
            "</span>";
    p3 = "<span>\n" +
            "    <p><h4>4. HOW DO WE HANDLE YOUR SOCIAL LOGINS? </h4></p>\n" +
            "    <p><i>In Short: If you choose to register or log in to our services using a social media account, we may\n" +
            "have access to certain information about you.</i></p>\n" +
            "<p>Our Apps offer you the ability to register and login using your third party social media account details\n" +
            "(like your Facebook or Twitter logins). Where you choose to do this, we will receive certain profile\n" +
            "information about you from your social media provider. The profile Information we receive may vary\n" +
            "depending on the social media provider concerned, but will often include your name, e-mail address,\n" +
            "friends list, profile picture as well as other information you choose to make public.</p>\n" +
            "<p>We will use the information we receive only for the purposes that are described in this privacy policy\n" +
            "or that are otherwise made clear to you on the Apps. Please note that we do not control, and are not\n" +
            "responsible for, other uses of your personal information by your third party social media provider. We\n" +
            "recommend that you review their privacy policy to understand how they collect, use and share your\n" +
            "personal information, and how you can set your privacy preferences on their sites and apps.</p>\n" +
            "</span>\n" +
            "\n" +
            "<span>\n" +
            "    <p><h4> 5. WHAT IS OUR STANCE ON THIRD-PARTY WEBSITES?</h4></p>\n" +
            "    <p><i>In Short: We are not responsible for the safety of any information that you share with third-party\n" +
            "providers who advertise, but are not affiliated with, our websites.</i></p>\n" +
            "<p> Apps may contain advertisements from third parties that are not affiliated with us and which may\n" +
            "link to other websites, online services or mobile applications. We cannot guarantee the safety and\n" +
            "privacy of data you provide to any third parties. Any data collected by third parties is not covered by\n" +
            "this privacy policy. We are not responsible for the content or privacy and security practices and\n" +
            "policies of any third parties, including other websites, services or applications that may be linked to\n" +
            "or from the Apps. You should review the policies of such third parties and contact them directly to\n" +
            "respond to your questions.</p>\n" +
            "</span>\n" +
            "\n" +
            "<span>\n" +
            "    <p><h4>6. HOW LONG DO WE KEEP YOUR INFORMATION?</h4></p>\n" +
            "    <p><i>In Short: We keep your information for as long as necessary to fulfill the purposes outlined in this\n" +
            "    privacy policy unless otherwise required by law.</i></p>\n" +
            "    <p>We will only keep your personal information for as long as it is necessary for the purposes set out in\n" +
            "    this privacy policy, unless a longer retention period is required or permitted by law (such as tax,\n" +
            "    accounting or other legal requirements). No purpose in this policy will require us keeping your\n" +
            "    personal information for longer than 2 years.</p>\n" +
            "    <p>When we have no ongoing legitimate business need to process your personal information, we will\n" +
            "    either delete or anonymize it, or, if this is not possible (for example, because your personal\n" +
            "    information has been stored in backup archives), then we will securely store your personal\n" +
            "    information and isolate it from any further processing until deletion is possible.</p>\n" +
            "</span>\n" +
            "\n" +
            "<span>\n" +
            "<p><h4>7. HOW DO WE KEEP YOUR INFORMATION SAFE? </h4></p>\n" +
            "<p><i>In Short: We aim to protect your personal information through a system of organizational and\n" +
            "technical security measures.</i></p>\n" +
            "<p>We have implemented appropriate technical and organizational security measures designed to\n" +
            "protect the security of any personal information we process. However, please also remember that\n" +
            "we cannot guarantee that the internet itself is 100% secure. Although we will do our best to protect\n" +
            "your personal information, transmission of personal information to and from our Apps is at your own\n" +
            "risk. You should only access the services within a secure environment.</p>\n" +
            "</span>\n";
    p4 = "<span>\n" +
            "\n" +
            "    <p><h4>8. DO WE COLLECT INFORMATION FROM MINORS?</h4></p>\n" +
            "    <p><i>In Short: We do not knowingly collect data from or market to children under 18 years of age.</i></p>\n" +
            "    <p>We do not knowingly solicit data from or market to children under 18 years of age. By using the\n" +
            "    Apps, you represent that you are at least 18 or that you are the parent or guardian of such a minor\n" +
            "    and consent to such minor dependent’s use of the Apps. If we learn that personal information from\n" +
            "    users less than 18 years of age has been collected, we will deactivate the account and take\n" +
            "    reasonable measures to promptly delete such data from our records. If you become aware of any\n" +
            "    data we have collected from children under age 18, please contact us at elvezamedia@gmail.com. </p>\n" +
            "</span>\n" +
            "\n" +
            "<span>\n" +
            "    <p><h4>9. WHAT ARE YOUR PRIVACY RIGHTS?</h4></p>\n" +
            "    <p><i>In Short: You may review, change, or terminate your account at any time.</i></p>\n" +
            "    <p>If you are resident in the European Economic Area and you believe we are unlawfully processing\n" +
            "    your personal information, you also have the right to complain to your local data protection\n" +
            "    supervisory authority. You can find their contact details here:\n" +
            "    http://ec.europa.eu/justice/data-protection/bodies/authorities/index_en.htm.</p>\n" +
            "</span>\n" +
            "\n" +
            "<span>\n" +
            "    <p><h4>10. CONTROLS FOR DO-NOT-TRACK FEATURES</h4></p>\n" +
            "<p>Most web browsers and some mobile operating systems and mobile applications include a\n" +
            "Do-Not-Track (“DNT”) feature or setting you can activate to signal your privacy preference not to\n" +
            "have data about your online browsing activities monitored and collected. No uniform technology\n" +
            "standard for recognizing and implementing DNT signals has been finalized. As such, we do not\n" +
            "currently respond to DNT browser signals or any other mechanism that automatically communicates\n" +
            "your choice not to be tracked online. If a standard for online tracking is adopted that we must follow\n" +
            "in the future, we will inform you about that practice in a revised version of this privacy policy.</p>\n" +
            "</span>\n" +
            "\n" +
            "<span>\n" +
            "<p><h4>11. SPECIFIC PRIVACY RIGHTS ON THOSE UNDER 18 YEARS OLD?</h4></p>\n" +
            "<p>If you are under 18 years of age, and have a registered account with the Apps, you have the right to\n" +
            "request removal of unwanted data that you publicly post on the Apps. To request removal of such\n" +
            "data, please contact us using the contact information provided below, and include the email address\n" +
            "associated with your account. We will make sure the data is not publicly displayed on the Apps, but\n" +
            "please be aware that the data may not be completely or comprehensively removed from our\n" +
            "systems.</p>\n" +
            "</span>\n" +
            "\n" +
            "<span>\n" +
            "    <p><h4>12. DO WE MAKE UPDATES TO THIS POLICY?</h4></p>\n" +
            "    <p><i>In Short: Yes, we will update this policy as necessary to stay compliant with relevant laws.</i></p>\n" +
            "    <p>We may update this privacy policy from time to time. The updated version will be indicated by an\n" +
            "    updated “Revised” date and the updated version will be effective as soon as it is accessible. If we\n" +
            "    make material changes to this privacy policy, we may notify you either by prominently posting a\n" +
            "    notice of such changes or by directly sending you a notification. We encourage you to review this\n" +
            "    privacy policy frequently to be informed of how we are protecting your information. </p>\n" +
            "</span>\n" +
            "\n" +
            "<span>\n" +
            "    <p><h4>13. HOW CAN YOU CONTACT US ABOUT THIS POLICY?</h4></p>\n" +
            "    <p>If you have questions or comments about this policy, you may email us at elvezamedia@gmail.com\n" +
            "    or by post to:</p>\n" +
            "    <p>\n" +
            "    Elveza Media<br/>\n" +
            "    Sungura plaza Ngong Town<br/>\n" +
            "    Nairobi, Ngong<br/>\n" +
            "    Kenya</p>   \n" +
            "</span>\n" +
            "\n" +
            "<span>\n" +
            "    <p><h4>HOW CAN YOU REVIEW, UPDATE, OR DELETE THE DATA WE\n" +
            "    COLLECT FROM YOU?</h4></p>\n" +
            "    <p>Based on the laws of some countries, you may have the right to request access to the personal\n" +
            "    information we collect from you, change that information, or delete it in some circumstances. To\n" +
            "    request to review, update, or delete your personal information, please send us an email on\n" +
            "    <a href=\"#\">elvezamedia@gmail.com</a>. We will respond to your request within 30 days.</p>\n" +
            "</span>";
    };

}
