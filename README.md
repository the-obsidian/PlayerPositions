# PlayerPositions [![Build Status](https://travis-ci.org/the-obsidian/PlayerPositions.svg?branch=master)](https://travis-ci.org/the-obsidian/PlayerPositions)

Synchronizes Minecraft groups with Discourse groups

## Dependencies

* Vault

## Installation

1. Download the [latest release](https://github.com/the-obsidian/PlayerPositions/releases) from GitHub
1. Add it to your `plugins` folder
1. Either run Bukkit/Spigot once to generate `PlayerPositions/config.yml` or create it using the guide below.
1. All done!

## Configuration

PlayerPositions has several options that can be configured in the `config.yml` file:

```yaml
# The URL of your Discourse installation (without the trailing slash)
discourse-url: http://forum.example.com

# A mapping between Discourse groups (by integer ID) and Minecraft groups (by name)
groups:

  # Add user to groupname when they are in Discourse group 4
  - discourse: 4
    minecraft: groupname

  # Remove user from groupname when they are not in Discourse group 4
  - discourse: 4
    minecraft: groupname
    remove: true

  # Add user to guestgroup if they do not have any Discourse groups
  - discourse: 0
    minecraft: guestgroup
```

`discourse` keys are the IDs of your chosen Discourse groups.  A negative number means the absence of the group, so `discourse: -20` would target users who were not a member of group `20`.  `0` is a special group meaning users who are not a member of any Discourse groups.

## Features

* Synchronizes Minecraft groups with Discourse groups on player join

## Upcoming Features

* Add more hooks for synchronization, including recurring tasks and possibly webhooks
