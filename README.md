# ARPGuard
(ARP Spoofing Detector Engine)

![Alt ARPGuard](./src/gl4di4tor/resources/icon_small.png)

## What is this?

This is a free-platform tool for protecting you against [ARP spoofing](https://en.wikipedia.org/wiki/ARP_spoofing) attacks on your local network.

**Attention** : This tool is a detector engine that detects and alerts you if you will be under ARP spoofing attacks and doesn't prevent of this kind of attacks.
 (For preventing ARP spoofing see [this documentation](http://security.stackexchange.com/questions/43373/how-to-prevent-arp-poisoning-on-my-network)).
  
## Pre-Installation

This tool has been written in java language, so is free-platform and you will need jre (>= 8) on your machine for running that.
See [this documentation](https://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html) for learn how to install jre. 

## Installation

There is two way for installation this tool:

1. Download latest version from [release page](https://github.com/arVahedi/ARPGuard/releases/latest).**(recommended for end-users)**

2. Clone or download repository and build from source.**(recommended for developers)**

## How to use?

For use this tool you should modify [configuration.properties](https://github.com/arVahedi/ARPGuard/blob/master/configuration.properties) file and set your local interface ip and router ip and trusted router mac address.After this step you can run jar file and you will be safe against ARP spoofing attacks.

**Note** : Currently version (1.5) supports only one local interface and only router mac address for protection.We are working strongly for remove this limitation in next version.

## Special thanks to a friend:

**MASOOM_YMD** for graphical design the logo
