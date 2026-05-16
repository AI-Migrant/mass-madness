import java.util.*;

class YouTubeTranscriptTimestampRemover {
	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder(100000);
 
		while(sc.hasNext()) {
			String s = sc.nextLine();
			String[] words = s.split(" ");
 
			if (!Character.isDigit(words[0].charAt(0)) || !(words[0].contains(":") || words[1].startsWith("second") || words[1].startsWith("minute"))) {
				sb.append(s);
				sb.append(' ');
			}
		}
 
		System.out.println(sb.toString());
	}
}

// An eample using the video [ICATOR - Updates and News - April 2026.](https://www.youtube.com/watch?v=mR3QCymxkIU)

/*Example Output
[music] Dear victims, dear members, dear Ekato supporters, before I start with the Ikato update, I would like to thank you wholeheartedly for all the um nice birthday wishes I received and the supporting messages. So now let's dive into the ecatone news. First United Nations advocacy and lobbering work. On the 9th of March 2026, we responded to the UN call for input, human rights in the administration of justice, neurochnology, and other emerging technologies aimed at informing the Secretary General's report to the General Assembly. As requested, we submitted 4,000 words uh as a as an English and French response to the call respecting the deadline. We gave permission to Icato's contribution to be made available on the UN human rights office website. We have since then received an acknowledgement of receipt by the UN registry team thanking us for our contribution. We are monitoring the UN rights office website for publication. However, we shall also be disseminating our reply to the UN call to our members, associates, and supporters. Second point, the TI one voice declaration platform created by Stefan Vander. As most of you probably know as we're sending regular updates and the development on the development progress of the TI1 voice platform Ecur is supporting Stefan has further enhanced it. As such he has added further security features giving uh users granular control over their profile um and file sharing permissions. More languages are available. birthday message services, daily substack, new posts, and more to come. Please connect to the platform at https1voice.witsk.org and or email Stefan at steep vidagmail.com for for more information. We will add the links and Stefan's contact details um to this video. Recently, the mailing platform of the UN voice tool has also permitted Ecato to raise awareness for Ecato's work and remote influencing technologies victims concerns with two European politicians. As such, a German member of the European Parliament as well as an Italian Italian Italian parliamentary assembly member of the Council of Europe have contacted Ekato expressing interest in our course and declaring their intent of disseminating our information. Third point, upcoming Ecato member meetings. Now that our internal issues have been successfully been brought to a close, IATU resumes its normal operations. We will organize our annual general assembly before 30 of June and provide you with updates and highlights of our highlights of our community and our association's life over the last running year. We are planning to invite TI1 voice platforms creator Stefan Fand to present the platform and reply to members questions and suggestions. We will also come up with a proposal aiming as at establishing a predictable monthly income stream. Unfortunately, we're struggling with numerous payment defaults or late membership um fee payments for which we need to find a permanent solution. Official dates and times will be announced shortly with full consideration for various time zones. Fourth point, special health care for remote influence techn technology victims. Last but not least, I would like to provide you with an update on a crucial subject, the the establishment of a planned TI clinic. It is of the ut utmost importance that there's a much needed specific care for patients who are victims of bioelectric weapons. Over the last months, I have therefore been instrumental in putting these essential medical care services in place with a multidisciplinary team of doctors ready to treat primarily our members but also the victim's community as a whole. As such, Ecatau's UK representative recently traveled to Belgium for a consultation with a medical specialist. The meeting was highly successful, providing critical insights that may prevent unnecessary surgery and ensure more accurate long-term treatment with the UK health care system. Regarding our internal process uh progress sorry development of the TI medical clinic remains ongoing. We are currently focused on the planning and preparation stages as we continue to secure the necessary funding for its completion. We therefore ask you for your support donating to Ecatau via our crowd link fund uh funding link fundraiser or by doing a bank transfer. All necessary financial details can be found on the membership and donation section of our website ecato.be as well as on the links listed here below. On behalf of Ecatau and the bioelectric weapon weapons victims worldwide we represent, I thank you in advance for your support. United, we are strong. Goodbye for now. Thank you. [music] 
*/

/* Example Input
[music]
0:23
23 seconds
Dear victims, dear members, dear Ekato supporters, before I start with the Ikato update,
0:30
30 seconds
I would like to thank you wholeheartedly for all the um nice birthday wishes I received and the supporting messages.
0:39
39 seconds
So now let's dive into the ecatone news.
0:43
43 seconds
First United Nations advocacy and lobbering work. On the 9th of March 2026, we responded to the UN call for
0:53
53 seconds
input, human rights in the administration of justice, neurochnology, and other emerging technologies aimed at informing the
1:01
1 minute, 1 second
Secretary General's report to the General Assembly.
1:05
1 minute, 5 seconds
As requested, we submitted 4,000 words uh as a as an English and French response to the call respecting the deadline.
1:16
1 minute, 16 seconds
We gave permission to Icato's contribution to be made available on the UN human rights office website.
1:24
1 minute, 24 seconds
We have since then received an acknowledgement of receipt by the UN registry team thanking us for our contribution.
1:34
1 minute, 34 seconds
We are monitoring the UN rights office website for publication. However, we
1:40
1 minute, 40 seconds
shall also be disseminating our reply to the UN call to our members, associates, and supporters.
1:48
1 minute, 48 seconds
Second point, the TI one voice declaration platform created by Stefan Vander.
1:56
1 minute, 56 seconds
As most of you probably know as we're sending regular updates and the development on the development progress of the TI1 voice platform Ecur is
2:05
2 minutes, 5 seconds
supporting Stefan has further enhanced it.
2:10
2 minutes, 10 seconds
As such he has added further security features giving uh users granular control over their profile um and file sharing permissions.
2:20
2 minutes, 20 seconds
More languages are available.
2:23
2 minutes, 23 seconds
birthday message services, daily substack, new posts, and more to come.
2:30
2 minutes, 30 seconds
Please connect to the platform at https1voice.witsk.org
2:42
2 minutes, 42 seconds
and or email Stefan at steep vidagmail.com for for more information. We will add
2:50
2 minutes, 50 seconds
the links and Stefan's contact details um to this video. Recently, the mailing platform of the UN voice tool has also
2:58
2 minutes, 58 seconds
permitted Ecato to raise awareness for Ecato's work and remote influencing technologies victims concerns with two European politicians.
3:09
3 minutes, 9 seconds
As such, a German member of the European Parliament as well as an Italian Italian Italian parliamentary assembly member of the Council of Europe have contacted
3:17
3 minutes, 17 seconds
Ekato expressing interest in our course and declaring their intent of disseminating our information.
3:26
3 minutes, 26 seconds
Third point, upcoming Ecato member meetings. Now that our internal issues have been successfully been brought to a
3:35
3 minutes, 35 seconds
close, IATU resumes its normal operations.
3:39
3 minutes, 39 seconds
We will organize our annual general assembly before 30 of June and provide you with updates and highlights of our
3:48
3 minutes, 48 seconds
highlights of our community and our association's life over the last running year.
3:54
3 minutes, 54 seconds
We are planning to invite TI1 voice platforms creator Stefan Fand to present the platform and reply to members questions and suggestions.
4:05
4 minutes, 5 seconds
We will also come up with a proposal aiming as at establishing a predictable monthly income stream. Unfortunately,
4:14
4 minutes, 14 seconds
we're struggling with numerous payment defaults or late membership um fee payments for which we need to find a permanent solution.
4:24
4 minutes, 24 seconds
Official dates and times will be announced shortly with full consideration for various time zones.
4:33
4 minutes, 33 seconds
Fourth point, special health care for remote influence techn technology victims. Last but not least, I would
4:41
4 minutes, 41 seconds
like to provide you with an update on a crucial subject, the the establishment of a planned TI clinic.
4:49
4 minutes, 49 seconds
It is of the ut utmost importance that there's a much needed specific care for patients who are victims of bioelectric weapons.
4:59
4 minutes, 59 seconds
Over the last months, I have therefore been instrumental in putting these essential medical care services in place
5:07
5 minutes, 7 seconds
with a multidisciplinary team of doctors ready to treat primarily our members but also the victim's community as a whole.
5:17
5 minutes, 17 seconds
As such, Ecatau's UK representative recently traveled to Belgium for a consultation with a medical specialist.
5:25
5 minutes, 25 seconds
The meeting was highly successful, providing critical insights that may prevent unnecessary surgery and ensure
5:34
5 minutes, 34 seconds
more accurate long-term treatment with the UK health care system.
5:40
5 minutes, 40 seconds
Regarding our internal process uh progress sorry development of the TI medical clinic remains ongoing.
5:49
5 minutes, 49 seconds
We are currently focused on the planning and preparation stages as we continue to secure the necessary funding for its completion.
6:00
6 minutes
We therefore ask you for your support donating to Ecatau via our crowd link fund uh funding link fundraiser or by doing a bank transfer.
6:12
6 minutes, 12 seconds
All necessary financial details can be found on the membership and donation section of our website ecato.be
6:21
6 minutes, 21 seconds
as well as on the links listed here below.
6:25
6 minutes, 25 seconds
On behalf of Ecatau and the bioelectric weapon weapons victims worldwide we represent, I thank
6:33
6 minutes, 33 seconds
you in advance for your support. United, we are strong. Goodbye for now. Thank you.
6:49
6 minutes, 49 seconds
[music]
*/