# advert-optimizer

## Test
Our advertising platform promotes mobile applications , it contains a campaign for each such application. Our publishers bring users that install and then use these applications. The platform is reported about the install event and other application usage events of these users, for example "app_open", "registration" and "purchase" events this stream of events is saved in a database.

To achieve quality goals we optimize campaigns by blacklisting publishers who do not qualify the campaign's expectations. For example, a campaign may expect the number of "purchase" events a publisher brings to be equal or greater than 10% of the number of installs that publishers brought, or else the publisher should be blacklisted on that campaign.
To maintain these publisher blacklists we have a job process (OptimizationJob) runs every hour.
Campaign objects contain an optimizationProps object that includes the following properties:
 * sourceEvent and measuredEvent - in the above example sourceEvent would be "install" and measuredEvent would be "purchase";
 * threshold - the minimum of occurrences of sourceEvent, if a publisher has less sourceEvents that the threshold, then he should not be blacklisted;
 * ratioThreshold - the minimum ratio of sourceEvent occurrences to measuredEvent occurrences.
Event objects contain their type, the campaignId and publisherId.

## Authors
* Pryimak Vasyl
