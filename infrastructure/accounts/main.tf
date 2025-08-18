module one {
source = "git::ssh://git@aa/bb/repoccc.git//somelib?ref=0.2.1"
}

something
something

module two {
source = "git::ssh://git@dd/ee/repoff.git//somelib?ref=0.2.1"
}
