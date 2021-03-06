# firebase-job-dispacher

The biggest issue with AlarmManager is that it works on the basis of time. For this issue, Google introduced JobScheduler which works well in most of the common conditions like charging of the device, availability of the network.

JobScheduler only works with API level >=21,  so JobScheduler also wasn’t able to fulfill my requirement.


Recently I worked on a project where I needed to perform a task once after every 3 hours of using the application. I thought of using the AlarmManager for this. Later I got to know that the task should also be performed whenever the network is available. In which case, AlarmManager will not be able to take care of the additional requirement.


Although Android provides us the GcmNetworkManager for the network availability requirement, there is another challenge while using GcmNetworkManager where a user doesn’t have Google play services installed in his/her mobile. If your application is running on the Lollipop or above version then GcmNetworkManager will work like a charm. It is same as JobScheduler in functionality as it is also using the same framework. But for the application having support below the Lollipop, you need to have Google play services installed in the device in which application is going to run. I know that it is rare that Android mobiles don’t have Google play services but I can’t take that risk. So I decided to use Android JobScheduler that is more suitable for these kinds of constraints. JobScheduler works great when our app wants to perform a specific task only in cases when-
1. User’s mobile gets connected to power supply.
2. There is a network availability.
